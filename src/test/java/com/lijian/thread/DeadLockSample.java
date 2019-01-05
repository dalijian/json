package com.lijian.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLockSample  extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }


    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained:" + first);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second) {
                System.out.println(this.getName() + " obtained:" + second);
            }
        }
    }

        public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1",lockA,lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2",lockB,lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
//    public static void main(String[] args) {
//        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
//        Runnable dlCheck = () -> {
//            long[] threadIds = mxBean.findDeadlockedThreads();
//            if (threadIds != null) {
//                ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
//                System.out.println("detected deadlock thread:");
//                for (ThreadInfo threadInfo : threadInfos) {
//                    System.out.println(threadInfo.getThreadName());
//
//                }
//            }
//        };
//
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);
//
//    }
}
