package com.study.notes.netty.codec.jackson;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class JacksonServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new JacksonDecoder<JacksonBean>(JacksonBean.class))
                .addLast(new JacksonEncoder())
                .addLast(new JacksonServerHandler());
    }
}
