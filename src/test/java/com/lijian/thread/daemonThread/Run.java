package com.lijian.thread.daemonThread;

public class Run {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setDaemon(true);
        thread.start();
        MyThread2 thread2 = new MyThread2();

        thread2.start();
        System.out.println(Thread.currentThread().getName());
//当主线程 main 停止后 守护线程 也停止了 ,添加myThread2 后 只有 main ，thread-1 线程停止了 ，守护线程 才停止
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("i leave thread ");
        Runnable task = ()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 5000000; i++) {
                if (i == 1000000) {
                    System.out.println(i);
                    System.out.println(Thread.currentThread().getName());


                }
            }
        };
        task.run();

    }
}
