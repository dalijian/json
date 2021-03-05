package com.lijian.concurrent.CountDownLatch;

import java.util.concurrent.TimeUnit;

public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parse1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("child thread sleep 5 seconds");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread parse2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");

            }
        });
        parse1.start();
        parse2.start();
        parse1.join();
        parse2.join();
        System.out.println("All parser finish");

    }
}
