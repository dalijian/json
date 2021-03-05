package com.lijian.netty.embeddedChannel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class FixedLengthFrameDecoderTest {
    /**
     *  测试 入栈
     */
    @Test
    public void testFixedFrameDecoded() {

        ByteBuf buf = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);

        }
        ByteBuf input = buf.duplicate();
        // FixedLengthFrameDecoder  每 3 个 byte 写入 frame 中
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));


        Assert.assertTrue(channel.writeInbound(input.retain()));


        Assert.assertTrue(channel.finish());

        // 每次 读取 会 读取 3 个 byte
        ByteBuf read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();


        read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();


        read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buf.release();
    }


    @Test
    public void testFramesDecoded2(){

        ByteBuf buf = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);

        }


        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));

        // 返回 false 因为 FixedLengthFrameDecoder 每次 读取 3 个 byte,  传递 2 个 byte 无法 读取
        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));

        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());

        // 每次 读取 会 读取 3 个 byte
        ByteBuf read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();


        read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();


        read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buf.release();
    }
}
