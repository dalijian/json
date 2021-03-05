package com.lijian.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 传入 ByteBuf 是 ReplayingDecoderByteBuf

//        从 入站 ByteBuf 中 读取 一个 int ,并 将 其 添加 到 解码 消息 的 list 中
        list.add(byteBuf.readInt());
    }
}
