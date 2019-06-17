package com.lijian.concurrent.CountDownLatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @Title: junit多线程测试
 * @ClassName: JunitMultiThreadTest.java
 * @Description:
 * @Copyright 2016-2018 - Powered By 研发中心
 * @author:
 * @date: 2018-01-30 19:31
 */
public class JunitMultiThreadTest {
    private int i = 5;
    /*
     * 线程计数器
     * 将线程数量初始化
     * 每执行完成一条线程，调用countDown()使计数器减1
     * 主线程调用方法await()使其等待，当计数器为0时才被执行
     */
    private CountDownLatch latch = new CountDownLatch(i);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < this.i; i++) {
            new Thread(new Runner(), "JUNIT多线程测试").start();
        }
        try {
            latch.await(); // 主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Runner implements Runnable {
        @Override
        public void run() {
            System.out.println("【当前线程ID】:" + Thread.currentThread().getId());
            latch.countDown(); // 执行完毕，计数器减1
        }
    }
}
