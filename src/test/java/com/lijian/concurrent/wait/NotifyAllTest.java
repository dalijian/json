package com.lijian.concurrent.wait;

public class NotifyAllTest {


    public static void main(String[] args) {

        NotifyDemo notifyDemo = new NotifyDemo();
        notifyDemo.notifyT();
//        notifyDemo.waitT();
    }
}
class NotifyDemo {

    synchronized void  notifyT() {
        this.notifyAll();
    }
    synchronized void waitT(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
