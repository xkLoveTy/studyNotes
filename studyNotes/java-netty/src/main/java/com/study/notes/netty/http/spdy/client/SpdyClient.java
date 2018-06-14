package com.study.notes.netty.http.spdy.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ApplicationProtocolNames;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * An SPDY client that allows you to send HTTP GET to a SPDY server.
 */
public class SpdyClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8443"));

    public static void main(String[] args) throws Exception {
        final SslContext sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .applicationProtocolConfig(new ApplicationProtocolConfig(
                        ApplicationProtocolConfig.Protocol.NPN,
                        // NO_ADVERTISE is currently the only mode supported by both OpenSsl and JDK providers.
                        ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE,
                        // ACCEPT is currently the only mode supported by both OpenSsl and JDK providers.
                        ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT,
                        ApplicationProtocolNames.SPDY_3_1,
                        ApplicationProtocolNames.HTTP_1_1
                )).build();

        HttpResponseClientHandler httpResponseHandler = new HttpResponseClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(HOST, PORT)
                    .handler(new SpdyClientInitializer(sslCtx, httpResponseHandler));

            Channel ch = b.connect().syncUninterruptibly().channel();
            System.out.println("Connected to " + HOST + ": " + PORT);

            HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "");
            request.headers().set(HttpHeaderNames.HOST, HOST);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

            ch.writeAndFlush(request).sync();

            httpResponseHandler.queue().take().sync();
            System.out.println("Finished SPDY HTTP GET");

             // Wait until the connection is closed.
             ch.close().syncUninterruptibly();
        } finally {
            group.shutdownGracefully();
        }
    }
}
