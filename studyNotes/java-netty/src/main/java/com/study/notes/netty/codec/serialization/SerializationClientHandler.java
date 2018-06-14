package com.study.notes.netty.codec.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializationClientHandler extends SimpleChannelInboundHandler<Object> {
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof SerializationBean) {
            SerializationBean user = (SerializationBean) msg;
            System.out.println("Client get msg form Server - name:"
                    + user.getName() + ";age:" + user.getAge());
        }
    }
}
