package com.lijian.test;

import java.io.*;

public class PrintTest {
    public static void main(String[] args) {
//        for (int i = 0; i < 200; i++) {
//            System.out.println((char) i);
//
//        }

//        System.out.println((byte)'\t');

        String filePath = System.getProperty("user.dir") + File.separator + "readme.md";
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            int i;
            while ((i =inputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
