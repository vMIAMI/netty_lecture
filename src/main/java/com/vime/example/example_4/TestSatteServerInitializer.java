package com.vime.example.example_4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;


//责任链模式 ??

public class TestSatteServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //超时事件处理器,分别对应 读 写 读|写
        pipeline.addLast("dleStateHandler",new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
        pipeline.addLast("testChatServerHandler",new TestStateServerHandler());
    }
}
