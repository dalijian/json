package com.lijian.thread.chapter5.useConditionWaitNotifyOK;

public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a=new ThreadA(service);
        a.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.signal();
    }
}
