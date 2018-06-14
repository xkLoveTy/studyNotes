package com.study.notes.netty.codec.jackson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class JacksonDecoder<T> extends ByteToMessageDecoder {
    private final Class<T> clazz;

    public JacksonDecoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBufInputStream  byteBufInputStream = new ByteBufInputStream(in);
        out.add(JacksonMapper.getInstance().readValue(byteBufInputStream, clazz));
    }
}
