package com.lijian.concurrent.threadPool;

import com.lijian.test.Run;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Slf4j
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), (Runnable r
        ) -> {
            Thread thread = new Thread(r);
            thread.setName("thread_" + thread.getId());
            return thread;
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
        try {
            threadPool.execute(() -> {
                System.out.println(1 / 0);
            });
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());

        } catch (Throwable throwable) {
            log.error(throwable.toString());

        }


    }
}
