package com.lijian.muti_thread.future.sample;

public class FutureData implements Data {
    private RealData realdata = null;
    private boolean ready = false;
    public synchronized void setRealData(RealData realdata) {
        if (ready) {
            return;     // balk
        }
        this.realdata = realdata;
        this.ready = true;
        notifyAll();
    }
//    多版本if 拿不到就 等待 （ Guarded Suspension） 设计模式
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realdata.getContent();
    }
}
