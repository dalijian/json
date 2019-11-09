package com.lijian.concurrent.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Demo {
    RateLimiter limiter = RateLimiter.create(1);

    @Test
    public void test() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 20; i++) {

            es.execute(()->{
                double waitTime = limiter.acquire();
                System.out.println("waiTime:" + waitTime);
            });

        }

        Thread.sleep(10000L);
    }


    @Test
    public void test2(){

        RateLimiter limiter = RateLimiter.create(1);


        ExecutorService es = Executors.newFixedThreadPool(1);



        for (int i = 0; i < 200; i++) {
            AtomicLong prev = new AtomicLong(System.nanoTime());
            System.out.println("prev'time:"+prev.get());
            limiter.acquire();
            es.execute(()->{
                long cur=System.nanoTime();

                System.out.println((cur- prev.get())/1000_000);
                prev.set(cur);
            });
        }


    }
}
