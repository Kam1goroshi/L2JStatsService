package com.l2eminence;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MyDebugHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof ByteBuf) {
                ByteBuf buf = (ByteBuf) msg;
                System.out.println("Received packet:");
                System.out.println("  Length: " + buf.readableBytes());
                System.out.println("  Data:   " + buf.toString(io.netty.util.CharsetUtil.UTF_8));
            } else {
                System.out.println("Received message: " + msg);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}