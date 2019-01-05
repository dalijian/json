package com.lijian.thread.useReturnInterrupt;

import org.junit.Test;

public class Mythread extends Thread {
@Override
    public void run(){
    while (true) {
        if (this.isInterrupted()) {
            System.out.println("stop");
            return;
        }
        System.out.println("timer=" + System.currentTimeMillis());

    }


}

}
