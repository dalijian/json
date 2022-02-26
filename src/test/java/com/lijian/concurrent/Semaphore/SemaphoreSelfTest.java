package com.lijian.concurrent.Semaphore;

import com.lijian.concurrent.lock.BoundedQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreSelfTest {


    private static final int THREAD_COUNT =30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
  static   SemaphoreSelf s = new SemaphoreSelf(2,new  BoundedQueue(2));
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    s.down();
                    System.out.println(Thread.currentThread().getState().toString());
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s.up();
                }
            });
        }

        threadPool.shutdown();
    }
}
