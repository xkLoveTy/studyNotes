package com.study.notes.netty.codec.jackson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JacksonClientHandler extends SimpleChannelInboundHandler<Object> {
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String jsonString = "";
        if (msg instanceof JacksonBean) {
            JacksonBean user = (JacksonBean) msg;
            jsonString = JacksonMapper.getInstance().writeValueAsString(user);
        } else {
            jsonString = JacksonMapper.getInstance().writeValueAsString(msg);
        }

        System.out.println("Client get msg form Server -" + jsonString);
    }
}
