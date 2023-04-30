package com.l2eminence;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import com.l2eminence.DbMessenger;
/**
 * Handles a server-side channel.
 */
public class myServerHandler extends ChannelInboundHandlerAdapter { // (1)
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) 
    {   //ignore whatever was said and write.
        ctx.write(DbMessenger.getImportantStatistics());
        ctx.flush();
        System.out.println("Buddy wtf?");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}