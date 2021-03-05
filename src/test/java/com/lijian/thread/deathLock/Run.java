package com.lijian.thread.deathLock;

public class Run {
    public static void main(String[] args) {
        DeathLock deathLock = new DeathLock();
        DeathTryLock deathLock2 = new DeathTryLock();
        ThreadA threadA = new ThreadA(deathLock2);
        ThreadB threadB = new ThreadB(deathLock2);
//        threadA.run();
//        threadB.run();
        Thread thread1 = new Thread(threadA);
        Thread thread2 = new Thread(threadB);
        thread1.start();
        thread2.start();
    }
}
