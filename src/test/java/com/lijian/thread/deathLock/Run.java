package com.lijian.thread.deathLock;

public class Run {
    public static void main(String[] args) {
        DeathLock deathLock = new DeathLock();
        ThreadA threadA = new ThreadA(deathLock);
        ThreadB threadB = new ThreadB(deathLock);
//        threadA.run();
//        threadB.run();
        Thread thread1 = new Thread(threadA);
        Thread thread2 = new Thread(threadB);
        thread1.start();
        thread2.start();
    }
}
