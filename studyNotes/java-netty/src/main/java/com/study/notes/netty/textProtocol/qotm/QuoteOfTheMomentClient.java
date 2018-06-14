package com.study.notes.netty.textProtocol.qotm;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * A UDP broadcast client that asks for a quote of the moment (QOTM) to {@link QuoteOfTheMomentServer}.
 */
public class QuoteOfTheMomentClient {
    static final int PORT = Integer.parseInt(System.getProperty("port", "7686"));

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new QuoteOfTheMomentClientHandler());

            //Broadcast the QOTM request to port 8080.
            Channel ch = b.bind(0).sync().channel();
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QTOM?", CharsetUtil.UTF_8),
                    new InetSocketAddress("255.255.255.255", PORT))).sync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
