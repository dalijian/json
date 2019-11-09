package com.lijian.concurrent.CyclicBarrier;

import java.util.concurrent.atomic.AtomicInteger;

public class SimulatedCAS {
    volatile  int count ;



    public static void main(String[] args) {

    }
    void addOne(){
        int newValue;
        do {
            newValue = count + 1;

        } while (count != cas(count, newValue));

    }

    synchronized int cas(int expect, int newValue) {

        int curValue =count;

        if (curValue == expect) {
            count=newValue;
        }
        return curValue;
    }
}
