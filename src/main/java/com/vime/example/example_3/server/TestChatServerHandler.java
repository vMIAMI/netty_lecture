package com.vime.example.example_3.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class TestChatServerHandler extends SimpleChannelInboundHandler<String> {
    //netty提供的所有正连接的channelGroup
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(c ->{
            channelGroup.writeAndFlush(channel == c? "self": channel.remoteAddress()
                    + " send ：" + msg);
        } );
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //给所有连接客户端发送消息
        channelGroup.writeAndFlush("client add : " + ctx.channel().remoteAddress());
        channelGroup.add(ctx.channel());
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //在这里是否自己能收到，channelGroup.remove()在super.handlerRemoved()中执行；
        //channelGroup.writeAndFlush("client remove : " + ctx.channel().remoteAddress());
        super.handlerRemoved(ctx);
        channelGroup.writeAndFlush("client remove : " + ctx.channel().remoteAddress());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
