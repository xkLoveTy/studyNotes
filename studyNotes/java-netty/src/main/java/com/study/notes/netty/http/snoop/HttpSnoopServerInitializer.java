package com.study.notes.netty.http.snoop;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class HttpSnoopServerInitializer extends ChannelInitializer<SocketChannel> {
    private SslContext sslCtx;
    public HttpSnoopServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null)
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));

        // Uncomment the following line if you don't want to handle HttpChunks.
        //pipeline.addLast(new HttpObjectAggregator(1048576));
        pipeline.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                // Remove the following line if you don't want automatic content compression.
                //pipeline.addLast(new HttpContentCompressor())
                .addLast(new HttpSnoopServerHandler());
    }
}
