package com.vime.example.example_6.server;

import com.vime.protocmsg.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestChatServerHandler extends SimpleChannelInboundHandler<DataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Person msg) throws Exception {
        System.out.print("server received : ");
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
