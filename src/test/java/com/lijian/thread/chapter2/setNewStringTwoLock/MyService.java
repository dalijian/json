package com.lijian.thread.chapter2.setNewStringTwoLock;

public class MyService {
    private String lock = "123";
    public  void testMethod(){
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "begin" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock = "456";
            System.out.println(Thread.currentThread().getName() + "end" + System.currentTimeMillis());

        }
    }
}
