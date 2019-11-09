package com.lijian.encodeAnddecode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class DecodeTest {

    @Test
    public void decodeTest() throws UnsupportedEncodingException {
        String str;
        str = new String("寰\uE1C0\uE5D3".getBytes("UTF-8"),"GBK");
        System.out.println(str);
    }
}
