package com.lijian.thread.priority;

public class Run {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyThread1 thread1 = new MyThread1();
            //设置线程优先级 数字高的先执行
            thread1.setPriority(1);
            thread1.start();
            MyThread2 myThread2 = new MyThread2();
            myThread2.setPriority(10);
            myThread2.start();
        }
    }
}
