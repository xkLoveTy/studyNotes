package com.study.notes.netty.binaryProtocol.factorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.math.BigInteger;

/**
 *
 * Encodes a {@link Number} into the binary representation prepended with
 * a magic number ('F' or 0x46) and a 32-bit length prefix.  For example, 42
 * will be encoded to { 'F', 0, 0, 0, 1, 42 }.

 * Created by xk_mac on 2017/8/8.
 */
public class NumberEncoder extends MessageToByteEncoder<Number> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Number msg, ByteBuf out) throws Exception {
        BigInteger v;

        if (msg instanceof BigInteger) {
            v = (BigInteger) msg;
        } else {
            v = new BigInteger(String.valueOf(msg));
        }

        byte[] data = v.toByteArray();
        int dataLength = data.length;

        // Write a message.
        out.writeByte((byte) 'F');
        out.writeInt(dataLength);
        out.writeBytes(data);
    }
}
