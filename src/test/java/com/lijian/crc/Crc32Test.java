package com.lijian.crc;

import java.util.zip.CRC32;

public class Crc32Test {


    public static void main(String[] args) {

        byte[] b = new byte[100];//用于验证的数据
        CRC32 c = new CRC32();
        c.reset();//Resets CRC-32 to initial value.
        c.update(b, 0, b.length);//将数据丢入CRC32解码器
        long value = c.getValue();//获取CRC32 的值  默认返回值类型为long 用于保证返回值是一个正数
        System.out.println(value);
    }
}
