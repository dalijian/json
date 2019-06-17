package com.lijian.thread.chapter2.syn_out_asyn;

public class ThreadA extends Thread {
    private MyList object;

    public ThreadA(MyList object) {

        super();
        this.object=object;

    }
    @Override
    public void run(){
        for (int i = 0; i < 100000; i++) {
            object.add("threadA" + (i + 1));
        }

    }
}
