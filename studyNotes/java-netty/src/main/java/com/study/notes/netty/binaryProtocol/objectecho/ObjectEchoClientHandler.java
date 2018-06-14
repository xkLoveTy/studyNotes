package com.study.notes.netty.binaryProtocol.objectecho;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Handler implementation for the object echo client.  It initiates the
 *  ping-pong traffic between the object echo client and server by sending the
 *  first message to the server.
 */
public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter {
    private final List<Integer> firstMessage;

    public List<Integer> getFirstMessage() {
        return firstMessage;
    }

    /**
     * Creates a client-side handler.
     */
    public ObjectEchoClientHandler() {
       firstMessage = new ArrayList<Integer>(ObjectEchoClient.SIZE);
       for (int i = 0; i < ObjectEchoClient.SIZE; i ++) {
               firstMessage.add(Integer.valueOf(i));
       }
   }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the first message if this handler is a client-side handler.
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Echo back the received object to the server.
        ctx.write(firstMessage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
