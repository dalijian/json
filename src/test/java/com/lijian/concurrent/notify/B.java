package com.lijian.concurrent.notify;

public class B {

    /**
     *  应该 是 B b = new B() , 不持有 B.class 对象 ， 无法 对 B.class  加锁
     *  wait () 又不是 静态方法 不支持 对 B 的 静态方法 调用
      */
   void lockB(){
        synchronized (B.class) {
            try {
                System.out.println("");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    synchronized void unlockB(){
      notifyAll();
    }
}
