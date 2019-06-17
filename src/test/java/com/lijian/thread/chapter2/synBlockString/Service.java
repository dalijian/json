package com.lijian.thread.chapter2.synBlockString;

public class Service {

    private String usernameParam;
    private String passwordParam;
    String anyString = new String();

    public void setUsernameParam(String usernameParam, String passwordParam) {

        synchronized (anyString) {
            System.out.println("线程名称为" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步快");
            usernameParam=usernameParam;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            passwordParam=passwordParam;
            System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步快");

        }
    }
}
