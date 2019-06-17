package com.lijian.thread.chapter4.test1;

public class MyThread2  extends Thread{
    private Object lock;

    public MyThread2(Object lock) {
        super();
        this.lock=lock;

    }

    @Override
    public void run(){
        synchronized (lock) {
            System.out.println("begin notify time=" + System.currentTimeMillis()+Thread.currentThread().getName());
            try {
                lock.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end notify time" + System.currentTimeMillis()+Thread.currentThread().getName());

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

}
