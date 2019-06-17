package com.lijian.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncronizedExample {
 
    public void test1() {
        System.out.println("执行方法1");
        for (int i = 0; i < 10; i++) {
            System.out.println("test1 - " + i);
        }
    }
 
    public static void main(String[] args) {
        //这样使用肯定是同步的
        SyncronizedExample syncronizedExample = new SyncronizedExample();
//        syncronizedExample.test1();
//        syncronizedExample.test1();
 
 
        // 如果遍历10次，会产生一种错觉，用线程池并发居然也是同步的！
        ExecutorService executorService = Executors.newFixedThreadPool(2);
 
        executorService.execute(() -> {
            syncronizedExample.test1();
        });
 
        executorService.execute(() -> {
            syncronizedExample.test1();
        });
    }
}
