package com.vime.example.example_6.server;

import com.vime.protocmsg.DataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //netty对于protocbuf的编解码支持
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //只对特定消息支持 todo
        pipeline.addLast(new ProtobufDecoder(DataInfo.Person.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());

        /*
        * 对于该initializer可同时支持多种消息类型，主要两种解决方案
        *
        * 1. 利用netty特性，在消息内定义消息头，通过自定义解码器实现不同消息支持[netty_example]
        * 2. 消息体内枚举
        *       参考Person.proto下的MyMessage，其下定义一个enum和一个oneof字段
        * */
        pipeline.addLast(new TestChatServerHandler());
    }
}
