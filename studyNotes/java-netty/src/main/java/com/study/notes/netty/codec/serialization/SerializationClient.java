package com.study.notes.netty.codec.serialization;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SerializationClient {
    public static void main(String[] args) throws Exception{
        new SerializationClient("localhost", 8082).run();
    }

    private final String host;
    private final int port;

    public SerializationClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SerializationClientHandlerInitializer());

            Channel ch = b.connect(host, port).sync().channel();

            SerializationBean user;

            for (int i = 0; i < 100; i++) {
                user = new SerializationBean();
                user.setAge(i);
                user.setName("waylau");
                ch.write(user);
            }
            ch.flush();
            // 等待连接关闭
            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }


    }
}
