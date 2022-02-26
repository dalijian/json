package com.lijian.concurrent.wait;

public class Sub implements Runnable {
    private Count count;
    public Sub(Count count){
        this.count=count;
    } 

    @Override
    public void run() {
        while(true){
            count.sub();
        }

    }

}