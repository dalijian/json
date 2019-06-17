package com.lijian.thread.chapter8.threadgroup;

public class Run {

    public static void main(String[] args) {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread.currentThread.name= " + Thread.currentThread().getName());

            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread.currentThread.name= " + Thread.currentThread().getName());

            }
        };
        ThreadGroup group = new ThreadGroup("group");

        Thread threada = new Thread(group, a);
        Thread threadb = new Thread(group, b);
        threada.start();
        threadb.start();
        System.out.println("活动的线程数为:"+group.activeCount());
        System.out.println("线程组名称为" + group.getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());

    }
}
