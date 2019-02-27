package com.study.notes.netty.http.websocket;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author litianyi
 */
@Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println("(1)request headers :" + request.headers());
		System.out.println("(2)request uri :" + request.uri());
        System.out.println("(2)request path :" + request);

        request.setUri("/ws");
		ctx.fireChannelRead(request.retain());

    }

}
