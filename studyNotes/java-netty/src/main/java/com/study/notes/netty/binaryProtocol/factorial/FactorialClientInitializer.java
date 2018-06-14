package com.study.notes.netty.binaryProtocol.factorial;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.ssl.SslContext;

/**
 * Created by xk_mac on 2017/8/8.
 */
public class FactorialClientInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public FactorialClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc(), FactorialClient.HOST, FactorialClient.PORT));
        }

        //Enable stream compression
        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));

        pipeline.addLast(new BigIntegerDecoder());
        pipeline.addLast(new NumberEncoder());


        pipeline.addLast(new FactorialClientHandler());
    }
}
