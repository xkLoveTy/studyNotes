package com.study.notes.netty.codec.jackson;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonClient {
    private final String host;
    private final int port;

    public JacksonClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new JacksonClient("localhost", 8082).run();
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new JacksonClientHandlerInitializer());

            Channel ch = b.connect(host, port).sync().channel();
            JacksonBean user = new JacksonBean();
            user.setAge(27);
            user.setName("waylau");
            List<String> sons = new ArrayList<String>();
            for (int i = 0;i <10; i++) {
                sons.add("Lucy"+i);
                sons.add("Lily"+i);
            }

            user.setSons(sons);

            Map<String, String> addrs = new HashMap<String, String>();
            for (int i = 0;i <10; i++) {
                addrs.put("001"+i, "18998366112");
                addrs.put("002"+i, "15014965012");
            }

            user.setAddrs(addrs);

            ch.writeAndFlush(user);
            ch.closeFuture().sync();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
