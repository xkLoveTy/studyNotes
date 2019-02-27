package com.study.notes.netty.http.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhouhuawei on 2019/2/25.
 */
public class WebSocketServer {
    private final int port;

    public WebSocketServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(group); // 绑定线程池
            sb.channel(NioServerSocketChannel.class); // 指定使用的channel
            sb.localAddress(this.port);// 绑定监听端口
            sb.childHandler(new WebChannelInitHandler());
            ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定
            System.out.println(WebSocketServer.class + " started and listen on " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } finally {
            group.shutdownGracefully().sync(); // 释放线程池资源
        }
    }

    public static void main(String args[]){
        try {
            WebSocketServer nettyServer = new WebSocketServer(9000);
            nettyServer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
