package com.lijian.thread.chapter2.t1;

public class HasSelfPrivateNum {

    public void addI(String username) {
        int num =0;
        if (username.equals("a")) {
            num=100;
            System.out.println("a set over");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else {
            num=200;
            System.out.println("b set over");

        }
        System.out.println(username +" num="+num);
    }
}
