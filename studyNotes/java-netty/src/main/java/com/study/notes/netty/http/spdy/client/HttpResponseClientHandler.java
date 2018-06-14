package com.study.notes.netty.http.spdy.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * HTTPResponse is received.
 */
public class HttpResponseClientHandler extends SimpleChannelInboundHandler<HttpObject> {
    private final BlockingDeque<ChannelFuture> queue = new LinkedBlockingDeque();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

            System.out.println("STATUS: " + response.status());
            System.out.println("VERSION: " + response.protocolVersion());
            System.out.println();

            if (!response.headers().isEmpty()) {
                for (CharSequence name : response.headers().names()) {
                    for (CharSequence value : response.headers().getAll(name)) {
                        System.out.println("HEADER: " + name + " = " + value);
                    }
                }
                System.out.println();
            }
            if (HttpUtil.isTransferEncodingChunked(response)) {
                System.out.println("CHUNKED CONTENT {");
            } else {
                System.out.println("CONTENT {");
            }
        }

        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            System.out.print(content.content().toString(CharsetUtil.UTF_8));
            System.out.flush();
            if (content instanceof LastHttpContent) {
                System.out.println("} END OF CONTENT");
                queue.add(ctx.channel().newSucceededFuture());
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        queue.add(ctx.channel().newFailedFuture(cause));
        cause.printStackTrace();
        ctx.close();
    }

    public BlockingQueue<ChannelFuture> queue() {
        return queue;
    }
}
