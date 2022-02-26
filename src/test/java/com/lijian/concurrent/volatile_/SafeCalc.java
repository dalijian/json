package com.lijian.concurrent.volatile_;

public class SafeCalc {
     long value = 0L;
  Object object = new Object();
   long get() {
     synchronized (object) {
       return value;
     }

  }
    void addOne() {
    synchronized (object) {
      value += 1;
    }

  }
}