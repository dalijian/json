package com.lijian.test;

public class ThreadTest implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1= ()->{
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread one");

        };
        Runnable runnable2= ()->{
            System.out.println("thread two");

        };

        Thread thread = new Thread(runnable1);
        Thread thread1 = new Thread(runnable2);
        thread.start();
        thread1.start();
        System.out.println(Thread.currentThread().getName());

        thread1.join();
        thread.join();

    }
}
