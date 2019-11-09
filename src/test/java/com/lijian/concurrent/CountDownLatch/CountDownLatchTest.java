package com.lijian.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待结束");

            }
        }).start();
        c.await();
//
//        TimeUnit.SECONDS.sleep(4L);
        System.out.println(3);

    }
}
