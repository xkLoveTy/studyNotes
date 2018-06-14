package com.study.notes.netty.http.spdy.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.spdy.SpdyFrameCodec;
import io.netty.handler.codec.spdy.SpdyHttpDecoder;
import io.netty.handler.codec.spdy.SpdyHttpEncoder;
import io.netty.handler.codec.spdy.SpdySessionHandler;
import io.netty.handler.ssl.SslContext;

import static io.netty.handler.codec.spdy.SpdyVersion.SPDY_3_1;
import static io.netty.util.internal.logging.InternalLogLevel.INFO;

public class SpdyClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final int MAX_SPDY_CONTENT_LENGTH = 1024 * 1024; // 1 MB

    private final SslContext sslCtx;
    private final HttpResponseClientHandler httpResponseHandler;

    public SpdyClientInitializer(SslContext sslCtx, HttpResponseClientHandler httpResponseHandler) {
        this.sslCtx = sslCtx;
        this.httpResponseHandler = httpResponseHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("ssl", sslCtx.newHandler(ch.alloc()))
                .addLast("spdyFrameCodec", new SpdyFrameCodec(SPDY_3_1))
                .addLast("spdyFrameLogger", new SpdyFrameLogger(INFO))
                .addLast("spdySessionHandler", new SpdySessionHandler(SPDY_3_1, false))
                .addLast("spdyHttpEncoder", new SpdyHttpEncoder(SPDY_3_1))
                .addLast("spdyHttpDecoder", new SpdyHttpDecoder(SPDY_3_1, MAX_SPDY_CONTENT_LENGTH))
                .addLast("spdyStreamIdHandler", new SpdyClientStreamIdHandler())
                .addLast("httpHandler", httpResponseHandler);
    }
}
