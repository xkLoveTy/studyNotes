package com.study.notes.netty.http.snoop;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.ssl.SslContext;

public class HttpSnoopClientInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public HttpSnoopClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null)
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));

        // Uncomment the following line if you don't want to handle HttpContents.
        //pipeline.addLast(new HttpObjectAggregator(1048576));
        pipeline.addLast(new HttpClientCodec())
                .addLast(new HttpContentDecompressor())  // Remove the following line if you don't want automatic content decompression.
                .addLast(new  HttpSnoopClientHandler());
    }
}
