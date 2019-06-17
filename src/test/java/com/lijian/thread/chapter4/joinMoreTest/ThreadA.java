package com.lijian.thread.chapter4.joinMoreTest;

public class ThreadA extends Thread {
    private ThreadB b;

    public ThreadA(ThreadB b) {
        super();
        this.b =b;

    }
    @Override
    public void run(){
        synchronized (b) {
            System.out.println(" begin A threadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" end A THreadnam=" + " " + System.currentTimeMillis());

        }
    }

}
