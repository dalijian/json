package com.lijian.thread;

public class SynchronizeTest  extends Thread{

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            SafeCalc safeCalc = new SafeCalc();
            safeCalc.addOne();
        }
    }
    public static  class SafeCalc {
        long value =0L;
        long getOne(){
            return value;
        }
        synchronized void addOne(){
            value+=1;
        }
    }
}
