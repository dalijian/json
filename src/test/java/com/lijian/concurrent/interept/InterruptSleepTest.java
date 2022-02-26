package com.lijian.concurrent.interept;

/**
 *  thread.interrupt  ，线程从 sleep 中 醒来
 */
public class InterruptSleepTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+ " is end");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is interrupted inner");
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        if (thread.isInterrupted()) {
            System.out.println(thread.getName() + " is interrupted");
        }
    }
}
