package com.lijian.thread.deathLock;

public class ThreadB implements Runnable {
    private DeathLock deathLock;

    public ThreadB(DeathLock deathLock) {
        this.deathLock = deathLock;
    }

    @Override
    public void run() {
        this.deathLock.getB();
    }
}
