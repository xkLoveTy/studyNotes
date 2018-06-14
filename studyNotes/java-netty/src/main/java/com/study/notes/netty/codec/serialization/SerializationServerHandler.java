package com.study.notes.netty.codec.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializationServerHandler extends SimpleChannelInboundHandler<Object> {
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof SerializationBean) {
            SerializationBean user = (SerializationBean) msg;
            ctx.writeAndFlush(user);
            System.out.println("Server get msg form Client - name:"+ user.getName() + ";age:" + user.getAge());
        }
    }
}
