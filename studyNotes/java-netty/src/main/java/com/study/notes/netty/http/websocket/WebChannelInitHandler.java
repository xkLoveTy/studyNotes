package com.study.notes.netty.http.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by zhouhuawei on 2019/2/25.
 */
public class WebChannelInitHandler extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        System.out.println("connected...; Client:" + channel.remoteAddress());
        ChannelPipeline pipeline = channel.pipeline();
        //HTTP 编解码
        pipeline.addLast(new HttpServerCodec());
        //定义缓冲数据量
        pipeline.addLast(new HttpObjectAggregator(64*1024));

        // http
        pipeline.addLast(new HttpServerHandler());

        //处理WebSocket协议与握手
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //业务处理Handler
        pipeline.addLast(new WebSocketHandler());
    }
}
