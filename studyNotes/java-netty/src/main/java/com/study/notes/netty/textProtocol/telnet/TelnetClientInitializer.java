package com.study.notes.netty.textProtocol.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

public class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringEncoder ENCODER = new StringEncoder();
    private static final StringDecoder DECODER = new StringDecoder();

    private static final TelnetClientHandler CLIENT_HANDLER = new TelnetClientHandler();

    private SslContext sslCtx;

    public TelnetClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc(), TelnetClient.HOST, TelnetClient.PORT));
        }

        // Add the text line codec combination first,
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(ENCODER);
        pipeline.addLast(DECODER);

        //and then business logic.
        pipeline.addLast(CLIENT_HANDLER);
    }
}
