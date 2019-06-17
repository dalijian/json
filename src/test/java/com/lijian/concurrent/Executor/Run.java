package com.lijian.concurrent.Executor;

import org.junit.Test;

import java.time.LocalTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) {
        //自定义ThreadFactory接口，设置线性名
        Executor  executor = Executors.newCachedThreadPool((Runnable runnable)->{
            Thread thread = new Thread(runnable);
            thread.setName(" name " + Math.random());
            return thread;
        });
//        executor.execute(()->{
//            System.out.println(Threa.currentThread().getName());
//        });

        Executor executorSingle = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorSingle.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        ((ExecutorService) executorSingle).shutdown();
    }



        public  void test(){
            ExecutorService pool = Executors.newFixedThreadPool(4);

            for (int i = 0; i < 8; i++) {
                int finalI = i + 1;
                pool.submit(() -> {
                    try {
                        System.out.println("任务"+ finalI +":开始等待2秒,时间:"+LocalTime.now()+",当前线程名："+Thread.currentThread().getName());
                        Thread.sleep(2000);
                        System.out.println("任务"+ finalI +":结束等待2秒,时间:"+LocalTime.now()+",当前线程名："+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

            }
            pool.shutdown();
        }
    }


