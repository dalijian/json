package com.lijian.concurrent.CountDownLatch;

public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parse1 = new Thread(new Runnable() {
            @Override
            public void run() {

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
