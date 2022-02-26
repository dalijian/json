package com.lijian.concurrent.wait;

public class Count {
    private int num = 0;
    private boolean flag = false; // 标识
    //加法
    public synchronized void add() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.num++; //加
        System.out.println(Thread.currentThread().getName() + "........" + this.num);
        this.flag=true; //设置标识为true
        notifyAll(); //唤醒所有在线程池中冻结的线程，会把所有都唤醒
        
    }
    //减法
    public synchronized void sub() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.num--; //减
        System.out.println(Thread.currentThread().getName() + "........" + this.num);
        this.flag=false; //设置标识为true
        notifyAll(); //唤醒所有在线程池中冻结的线程，会把所有都唤醒
    }
}