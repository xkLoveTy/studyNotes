package com.study.notes.netty.textProtocol.qotm;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class QuoteOfTheMomentServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Random random = new Random();

    private static final String[] quotes = {
        "Where there is love there is life.",
        "First they ignore you, then they laugh at you, then they fight you, then you win.",
        "Be the change you want to see in the world.",
        "The weak can never forgive. Forgiveness is the attribute of the strong.",
    };

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        System.err.println(msg);
        if ("QOTM?".equals(msg.toString())) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QOTM: " + nextQuote(), CharsetUtil.UTF_8), msg.sender()));
        }
     }

    private String nextQuote() {
        int quoteId;
        synchronized (random) {
                quoteId = random.nextInt(quotes.length);
        }
        return quotes[quoteId];
    }
}
