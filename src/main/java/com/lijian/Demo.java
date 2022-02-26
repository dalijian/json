package com.lijian;

import java.util.UUID;

public class Demo {
    public static String str;
    public static void main(String[] args) throws InterruptedException {
        System.out.println(" li l ".trim());
        System.out.print("**");

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> System.out.println(UUID.randomUUID().toString()));
            thread.start();
        }

    }
    static {
        System.out.println("this is static");
        str = "this is static";
    }
}
