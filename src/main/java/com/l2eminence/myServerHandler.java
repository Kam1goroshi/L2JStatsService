package com.l2eminence;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * Handles a server-side channel.
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter { // (1)

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        ByteBuf content = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest){
            channelRead0(ctx, (FullHttpRequest)msg);
        }
            System.out.println("in reply handler");
        String response = DbMessenger.getImportantStatistics() + "\r\n";
        ByteBuf content = Unpooled.copiedBuffer(response.getBytes());
        ByteBuf header = Unpooled.copiedBuffer("HTTP/1.1 200 OK\r\nContent-Length: ".getBytes(),
                String.valueOf(content.readableBytes()).getBytes(), "\r\n\r\n".getBytes());
        ctx.write(header);
        ctx.write(content);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}