package com.study.notes.netty.textProtocol.qotm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * A UDP server that responds to the QOTM (quote of the moment) request to a {@link QuoteOfTheMomentClient}.
 */
public class QuoteOfTheMomentServer {

    static final int PORT = Integer.parseInt(System.getProperty("port", "7686"));

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
             .channel(NioDatagramChannel.class)
             .option(ChannelOption.SO_BROADCAST, true)
             .handler(new QuoteOfTheMomentServerHandler());

            b.bind(PORT).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
