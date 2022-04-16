package com.lijian.concurrent.notify;

public class SynchronizedTest {
    public static void main(String[] args) {
        /**
         *  thread 没有 共享 同一个 SynchronizedDemo() 对象 ，所以 锁 this 对象 没用
         */
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> new SynchronizedDemo().sleepT());
            thread.start();
        }
    }

}

class SynchronizedDemo {
    /**
     *  锁住 class  会导致 线程 执行 串行 化
     */
     void  sleepT() {
         synchronized (SynchronizedDemo.class) {
             System.out.println(Thread.currentThread().getName()+" start sleep");
             try {
                 Thread.sleep(2000L);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(Thread.currentThread().getName()+ " end sleep");
         }

    }
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