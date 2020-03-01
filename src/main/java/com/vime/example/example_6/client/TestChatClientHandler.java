package com.vime.example.example_6.client;

import com.vime.protocmsg.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestChatClientHandler extends SimpleChannelInboundHandler<DataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Person person = DataInfo.Person.newBuilder()
                .setName("t_name").setAge(18).setAddress("t_addr")
                .build();

        ctx.channel().writeAndFlush(person);
    }
}
