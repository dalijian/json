package com.lijian.thread.chapter2.syn_out_asyn;

public class ThreadB extends Thread {
    private MyList object;

    public ThreadB(MyList object) {

        super();
        this.object=object;

    }
    @Override
    public void run(){
        for (int i = 0; i < 100000; i++) {
            object.add("threadB" + (i + 1));
        }

    }
}
