package com.lijian.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 使用线程池 ，在线程挂掉后，会自动补偿
public class ThreadPoolDemo2 {
    public static void main(String[] args) {

        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().interrupt();

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

//    public static void main(String[] args) {
//        ExecutorService service =Executors.newSingleThreadScheduledExecutor((Runnable r)->{
//            Thread thread = new Thread(r);
//            thread.setName("scheduled_thread_" + thread.getId());
//            return thread;
//        });
//
//        service.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(1 / 0);
//          Thread.currentThread().interrupt();
//            System.out.println(Thread.interrupted());
//        });
//        service.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
////            System.out.println(1 / 0);
//            System.out.println(Thread.interrupted());
//        });
//    }
}
