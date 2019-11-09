package com.lijian.timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Demo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(15, (Runnable r) -> {
            Thread thread1 = new Thread(r);
            thread1.setName("schedule" + thread1.getId());
            return thread1;
        });

    }
}
