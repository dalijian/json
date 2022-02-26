package com.lijian.concurrent.notify;

public class NotifyTest {
    public static void main(String[] args) {
        NotifyDemo notifyDemo = new NotifyDemo();
//        notifyDemo.lockA();

        B b = new B();
        b.lockB();
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

    /**
     * IllegalMonitorStateException
     * 锁不了 A.class
     *
     */
    void lockA(){
        synchronized (A.class) {
            notifyAll();
        }
    }
}