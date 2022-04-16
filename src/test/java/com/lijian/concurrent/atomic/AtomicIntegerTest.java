package com.lijian.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
   static     AtomicInteger atomicInteger= new AtomicInteger();
  static CountDownLatch countDownLatch = new CountDownLatch(1000);
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                atomicInteger.incrementAndGet();
                countDownLatch.countDown();
            });
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
    }
}
