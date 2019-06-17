package com.lijian.thread.chapter2.setNewPropertiesLockOne;

public class Service {
    public void serviceMethodA(Userinfo userinfo) {
        synchronized (userinfo) {
            System.out.println(Thread.currentThread().getName());
            userinfo.setUsername("abc");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end time=" + System.currentTimeMillis());
        }
    }
}
