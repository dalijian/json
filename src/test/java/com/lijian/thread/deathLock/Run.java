package com.lijian.thread.deathLock;

public class Run {
//    public static void main(String[] args) {
//        DeathLock deathLock = new DeathLock();
//        DeathTryLock deathLock2 = new DeathTryLock();
//        ThreadA threadA = new ThreadA(deathLock2);
//        ThreadB threadB = new ThreadB(deathLock2);
////        threadA.run();
////        threadB.run();
//        Thread thread1 = new Thread(threadA);
//        Thread thread2 = new Thread(threadB);
//        thread1.start();
//        thread2.start();
//    }

    /**
     * 处于 blocked 中 不能响应 中断
     * @param args
     */
    public static void main(String[] args) {
    DeathLock deathLock = new DeathLock();
    Thread threadA = new Thread(() -> deathLock.getA());
    Thread threadB = new Thread(() -> deathLock.getB());
    threadA.start();
    threadB.start();
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    threadA.interrupt();
}
}
