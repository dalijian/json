package com.lijian.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;


import java.net.InetSocketAddress;
import java.util.List;

public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {

    private final InetSocketAddress remoteAddress;

    public LogEventEncoder(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LogEvent logEvent, List<Object> list) throws Exception {


        byte[] file = logEvent.getLogfile().getBytes(CharsetUtil.UTF_8);

        byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);

        ByteBuf buf = channelHandlerContext.alloc().buffer(file.length + msg.length + 1);

        // 写入 文件 名
        buf.writeBytes(file);
        // 写入 分割符
        buf.writeByte(LogEvent.SEPARATOR);
        // 写入 消息
        buf.writeBytes(msg);
        list.add(new DatagramPacket(buf, remoteAddress));





    }
}
