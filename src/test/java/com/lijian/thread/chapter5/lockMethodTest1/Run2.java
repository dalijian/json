package com.lijian.thread.chapter5.lockMethodTest1;

public class Run2 {
    public static void main(String[] args) {
        final Service2 service = new Service2();
        Runnable runnable = ()->{
            service.serviceMethod1();
        };
        Thread [] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);

        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程数：" + service.lock.getQueueLength() + "在等待获取锁");

    }
}
