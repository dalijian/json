package com.lijian.thread.chapter7.singleton_2;

public class MyThread  extends Thread{
    @Override
    public void run(){
        System.out.println(MyObject.getInstance4().hashCode());

    }

}
