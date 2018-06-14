package com.study.notes.netty.binaryProtocol.factorial;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Handler for a client-side channel.  This handler maintains stateful
 * information which is specific to a certain channel using member variables.
 * Therefore, an instance of this handler can cover only one channel.  You have
 * to create a new handler instance whenever you create a new channel and insert
 * this handler to avoid a race condition.
 *
 * Created by xk_mac on 2017/8/8.
 */
public class FactorialClientHandler extends SimpleChannelInboundHandler<BigInteger> {
    private ChannelHandlerContext ctx;
    private int receiveMassage;
    private int next = 1;
    final BlockingQueue<BigInteger> answer = new LinkedBlockingQueue<BigInteger>();


    public BigInteger getFactorial() {
        boolean interrupted = false;
        try {
            for (;;) {
                try {
                    return answer.take();
                } catch (InterruptedException ignore) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        sendNumber();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, final BigInteger msg) throws Exception {
        receiveMassage ++;
        if (receiveMassage == FactorialClient.COUNT) {
            // Offer the answer after closing the connection.
            ctx.channel().close().addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    boolean offerd = answer.offer(msg);
                    assert offerd;
                }
            });
        }
    }

    private void sendNumber() {
        ChannelFuture future = null;

        // Do not send more than 4096 numbers.
        for (int i = 0; i < 4096 && next <= FactorialClient.COUNT; i++) {
            future = ctx.write(Integer.valueOf(next));
            next++;
        }

        if (next <= FactorialClient.COUNT) {
            assert future != null;
            future.addListener(numberSender);
        }
    }

    private final ChannelFutureListener numberSender = new ChannelFutureListener() {
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                sendNumber();
            } else {
                channelFuture.cause().printStackTrace();
                channelFuture.channel().close();
            }
        }
    };
}
