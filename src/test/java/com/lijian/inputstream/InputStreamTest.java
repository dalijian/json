package com.lijian.inputstream;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;


public class InputStreamTest {



    @Test
    public void byteArrayInputStreamTest() throws IOException {


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello world".getBytes());
        System.out.println("第一次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));
        System.out.println("第二次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));

    }


    //byteArrayInputStream 支持 mark,reset   可以 重复 读取
    @Test
    public void byteArrayInputStreamTestReset() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello world".getBytes());

        System.out.println("第一次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));
        System.out.println("第二次");
        byteArrayInputStream.reset();
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));

    }
}
