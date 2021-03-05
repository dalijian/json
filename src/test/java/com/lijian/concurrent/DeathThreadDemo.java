package com.lijian.concurrent;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//死锁
public class DeathThreadDemo implements Runnable {

    private Object object1 = new Object();
    private Object object2 = new Object();


    public static void main(String[] args) {

        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
//        单独起个线程 监测 死锁
        Runnable dlCheck =()->{
            long [] threadIds = mxBean.findDeadlockedThreads();

            if (threadIds != null) {
                ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                System.out.println("deltected dealock threads");
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println(threadInfo.getThreadName());
                }
            }
        };

        ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();


        executors.scheduleWithFixedDelay(dlCheck, 5L, 10L, TimeUnit.SECONDS);
        
        Object object1 = new Object();
        Object object2 = new Object();
        DeathThreadDemo deathThreadDemo1 = new DeathThreadDemo("thread-1", object1, object2);
        DeathThreadDemo deathThreadDemo2 = new DeathThreadDemo("thread-2", object2, object1);
        Thread thread1 = new Thread(deathThreadDemo1);
        thread1.start();
        Thread thread2 = new Thread(deathThreadDemo2);
        thread2.start();
    }

    public DeathThreadDemo(String threadName, Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
        Thread.currentThread().setName(threadName);

    }

    @Override
    public void run() {
        synchronized (object1) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    @Test
    public void waitTest(){



    }
}
