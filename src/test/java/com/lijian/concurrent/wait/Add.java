package com.lijian.concurrent.wait;

public class Add implements Runnable {
    private Count count;
    public Add(Count count){
        this.count=count;
    } 

    @Override
    public void run() {
        while(true){
            count.add();
        }

    }

}

