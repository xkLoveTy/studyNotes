package com.study.notes.netty.http.spdy.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

public class SpdyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public SpdyServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(sslCtx.newHandler(ch.alloc()));
        // Negotiates with the browser if SPDY or HTTP is going to be used
        p.addLast(new SpdyOrHttpHandler());
    }
}
