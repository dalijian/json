package com.lijian.io.system_in_out;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SystemInDemo2 {
    public static void main(String args[]) throws Exception { // 所有异常抛出
        InputStream input = System.in; // 从键盘接收数据
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
            if (scanner.next().equals("0")) {
                System.exit(1);

            }
        }
//        BufferedReader buf = new BufferedReader(new InputStreamReader(input)); // 使用StringBuffer接收数据
//        System.out.print("请输入内容：");

//        System.out.println("输入的内容为："+buf.readLine());
//        int temp = 0;
//        while ((temp = input.read()) != -1) { // 接收内容
//            char c = (char) temp;
//            if (c == '\n') { // 退出循环，输入回车表示输入完成
//                break;
//            }
//            buf.append(c); // 保存内容
//        }
//        System.out.println("输入的内容为：" + buf);

        input.close(); // 关闭输入流
    }
};