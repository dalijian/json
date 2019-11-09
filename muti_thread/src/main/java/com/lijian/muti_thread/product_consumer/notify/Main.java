package com.lijian.muti_thread.product_consumer.notify;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Something something = new Something();
        ThreadOne threadOne = new ThreadOne(something);
        Thread thread = new Thread(threadOne);
        thread.start();
        TimeUnit.SECONDS.sleep(1L);
        Something.notifyAll2();
        System.out.println("***************");
//        something.notifyAll();
        System.out.println((String) (null));

    }
}
