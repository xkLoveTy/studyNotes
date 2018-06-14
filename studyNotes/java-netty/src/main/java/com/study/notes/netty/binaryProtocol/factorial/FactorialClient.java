package com.study.notes.netty.binaryProtocol.factorial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Sends a sequence of integers to a {@link FactorialServer} to calculate the factorial specified integer.
 * Created by xk_mac on 2017/8/8.
 */
public class FactorialClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8322"));
    static final int COUNT = 1000;

    public static void main(String[] args) throws Exception {
        final SslContext sslCtx;

        if (SSL) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new FactorialClientInitializer(sslCtx));

            ChannelFuture f = b.connect(HOST, PORT).sync();
            FactorialClientHandler handler = (FactorialClientHandler) f.channel().pipeline().last();

            System.err.format("Factorial of %,d, is : %,d", COUNT, handler.getFactorial());
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }


}
