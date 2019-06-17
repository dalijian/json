package com.lijian.thread.deathLock;

public class ThreadA implements Runnable {
    private DeathLock deathLock;

    public ThreadA(DeathLock deathLock) {
        this.deathLock = deathLock;
    }

    @Override
    public void run() {
        this.deathLock.getA();
    }
}
