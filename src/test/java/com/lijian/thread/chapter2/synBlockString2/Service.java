package com.lijian.thread.chapter2.synBlockString2;

public class Service {

    private String usernameParam;
    private String passwordParam;
    String anyString = new String();
public void a () {
    synchronized (anyString) {
        System.out.println("a begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a end");

    }
}
    synchronized public void b(){
        System.out.println("b begin");
        System.out.println("b end");

    }

}
