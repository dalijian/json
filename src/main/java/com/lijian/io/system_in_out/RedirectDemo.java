package com.lijian.io.system_in_out;

import java.io.*;

public class RedirectDemo {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir")+File.separator+"readme.md")); // 绑定输入文件
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir")+File.separator+"test.txt"))); // 绑定输出文件

        // 设置重定向
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
            System.setOut(console);
        }
        out.close();
    }
}