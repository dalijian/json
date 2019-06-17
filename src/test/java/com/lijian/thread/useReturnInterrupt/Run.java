package com.lijian.thread.useReturnInterrupt;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Mythread t= new Mythread();
        t.start();
//        Threa.sleep(2000);
        t.sleep(2000);

        t.interrupt();

    }
}
