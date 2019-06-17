package com.lijian.thread.chapter2.t3;

public class PublicVar {
    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username, String password) {
        this.username=username;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password=password;
        System.out.println("setvalue method thread name=" + Thread.currentThread().getName() + "username=" + username + "password=" + password);
    }
    synchronized  public void getValue(){
              System.out.println("getvalue method thread name=" + Thread.currentThread().getName() + "username=" + username + "password=" + password);

    }
}
