package com.lijian.thread.chapter4.test1;

public class MyThread1  extends Thread{
    private Object lock;

    public MyThread1(Object lock) {
        super();
        this.lock=lock;

    }

    @Override
    public void run(){
        synchronized (lock) {
            System.out.println("begin wait time=" + System.currentTimeMillis()+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);

                lock.wait();
                System.out.println("end wait time" + System.currentTimeMillis()+Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
