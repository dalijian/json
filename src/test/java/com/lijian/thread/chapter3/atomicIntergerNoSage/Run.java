package com.lijian.thread.chapter3.atomicIntergerNoSage;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        MyThread[] array = new MyThread[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new MyThread(service);

        }
        for (int i = 0; i < array.length; i++) {
            array[i].start();
        }
        Thread.sleep(1000);
        System.out.println(service.airef.get());

    }
}
