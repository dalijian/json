package com.lijian.encodeAnddecode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class DecodeTest {

    /**
     *  unicode 转 utf-8  python 可以 转 ， java 不知道
     * @throws UnsupportedEncodingException
     */
    @Test
    public void decodeTest() throws UnsupportedEncodingException {
        String str;

        str = new String("寰\uE1C0\uE5D3".getBytes("UTF-8"),"GBK");
        System.out.println(str);
    }
}
