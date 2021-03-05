package com.lijian.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 4) { // 检查 是否 至少 4 个 字节 可读
            list.add(byteBuf.readInt());    // 从 入站 ByteBuf 中 读取 一个 int ,并 将 其 添加 到 解码 消息 的 list 中

        }
    }
}
