package com.lijian.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrent {
    @Test
    public void asyn() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(()->{
            String qq = null;
            System.out.println("start ");
            try {
                qq = pool.submit(() -> {
                    Thread.sleep(50000);
                    return "QQ";
                }).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("end");
            System.out.println(qq);

        });
        Thread.sleep(20000);
    }
}
