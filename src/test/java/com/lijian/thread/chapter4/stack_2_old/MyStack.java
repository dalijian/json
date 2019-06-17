package com.lijian.thread.chapter4.stack_2_old;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List list = new ArrayList();
    synchronized public void push(){
        if (list.size() == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add("anyString= " + Math.random());
        this.notify();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("push= "+list.size());
    }

    synchronized public String pop(){
        String returnValue = "";
        if (list.size() == 0) {
            System.out.println("pop操作中的:"+Thread.currentThread().getName()+" 线程是wait状态");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        returnValue = "" + list.get(0);
        list.remove(0);
        this.notify();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("pop=" + list.size());
        return returnValue;
    }


}
