package com.lijian.thread.chapter3.t16;

public class Run {

    public static void main(String[] args) {

        RunThread thread = new RunThread();

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setRunning(false);
        System.out.println("已经赋值为false");

    }
}
