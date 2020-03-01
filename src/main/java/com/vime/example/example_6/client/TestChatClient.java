package com.vime.example.example_6.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestChatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new TestChatClientInitializer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            channel.closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
