package com.study.notes.netty.http.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author litianyi
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
	/**
	 * 接收客户端处理方法
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) {
        System.out.println("Client:[" + ctx.channel().id() + "] channelRead0,msg");
        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) msg;

            System.out.println("Client:[" + ctx.channel().id() + "] channelRead0，msg:" + textFrame.text());
            if (textFrame.text().equals("ping")) {
                System.out.println("Client:" + textFrame.text());
            }
        } else {
            System.out.println("Client:[" + ctx.channel().id() + "] channelRead0 不处理非TextWebSocketFrame!");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel channelActive");
    }
}
