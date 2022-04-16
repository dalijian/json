package com.lijian.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
   static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
//                System.out.println(atomicLong.incrementAndGet());
                atomicLong.incrementAndGet();
                if (atomicLong.get() != atomicLong.get()) {
                    System.out.println("true");
                }
//                System.out.println(atomicLong.get());
            });
            thread.start();

        }

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicLong.get());
    }
}
