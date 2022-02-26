package com.lijian.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
  static   ExecutorService executorService = Executors.newFixedThreadPool(4,(Runnable r) -> {
        Thread thread = new Thread(r);
        thread.setName("check_unit_build_floor_thread" + thread.getId());
        return thread;
    });
}
