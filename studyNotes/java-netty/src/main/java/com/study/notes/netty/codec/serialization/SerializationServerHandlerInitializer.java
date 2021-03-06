package com.study.notes.netty.codec.serialization;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class SerializationServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    private final static int MAX_OBJECT_SIZE = 1024 * 1024;

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ObjectDecoder(MAX_OBJECT_SIZE,
                ClassResolvers.weakCachingConcurrentResolver(this.getClass()
                        .getClassLoader())));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new SerializationServerHandler());
    }
}
