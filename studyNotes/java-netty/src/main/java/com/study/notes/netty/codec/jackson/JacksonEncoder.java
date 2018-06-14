package com.study.notes.netty.codec.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JacksonEncoder extends MessageToByteEncoder<Object> {
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        ObjectMapper mapper = JacksonMapper.getInstance();
        /*byte[] body = mapper.writeValueAsBytes(msg);
        out.writeBytes(body);*/

        ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(out);
        mapper.writeValue(byteBufOutputStream, msg);
    }
}
