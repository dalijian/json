package com.lijian.thread.chapter3.t16;

public class RunThread  extends Thread{
//    volatile  private boolean isRunning =true;
    private boolean isRunning =true;

    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run(){
         String name = "lijain";
        System.out.println(" into run");
        System.out.println("current Threa : "+Thread.currentThread().getName());
        //为什么 while 中 有 操作 就 会 线程私有内存会与共有内存交换 然后isRunning为 false 线程停下来
        while (isRunning) {

        }
        System.out.println("thread will stop");

    }


}
