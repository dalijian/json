package com.lijian.netty.embeddedChannel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class AbsIntegerEncoderTest {

//测试 出栈 消息
    @Test
    public void testEncoded(){

        ByteBuf buf  =Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        Assert.assertTrue(channel.writeOutbound(buf));
        Assert.assertTrue(channel.finish());


        for (int  i = 0; i < 10; i++) {
            Assert.assertEquals(i, java.util.Optional.ofNullable(channel.readOutbound()).get());


        }

        Assert.assertNull(channel.readOutbound());
    }
}
