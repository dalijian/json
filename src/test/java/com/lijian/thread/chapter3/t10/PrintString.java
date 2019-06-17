package com.lijian.thread.chapter3.t10;

public class PrintString implements Runnable {
    private  boolean isContinuePrint = true;
    public boolean isContinuePrint(){
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }
    public void printStringMethod(){
        while (isContinuePrint) {
            System.out.println("run printStringMethod threandName=" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}
