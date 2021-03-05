package com.lijian.muti_thread.balking.sample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InitTest {

    boolean inited = false;

    ThreadPoolExecutor factory =  new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), r -> {
        return new Thread(r, "thread");

    });
}
