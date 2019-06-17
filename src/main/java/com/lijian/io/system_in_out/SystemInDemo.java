package com.lijian.io.system_in_out;

import java.io.InputStream;

public class SystemInDemo {
    public static void main(String args[]) throws Exception {
        InputStream input = System.in;
        
        byte b[] = new byte[5]; // 开辟空间，接收数据
        System.out.print("请输入内容：");
        
        int len = input.read(b); // 接收数据
        System.out.println("输入的内容为：" + new String(b, 0, len));
        
        input.close(); // 关闭输入流
    }
};