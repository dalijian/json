package com.lijian.io.writer_reader;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;

public class WriterDemo {
    public static void main(String args[]) throws Exception { // 异常抛出，不处理
        // 第1步、使用File类找到一个文件
        File f = new File(System.getProperty("user.dir") + File.separator + "test.txt"); // 声明File对象

        // 第2步、通过子类实例化父类对象
        Writer out = new FileWriter(f);
        // Writer out = new FileWriter(f, true); // 追加内容方式

        // 第3步、进行写操作
        String str = "Hello World!!!\r\n";
        out.write(str); // 将内容输出

        // 第4步、关闭输出流
        out.close();
    }
}