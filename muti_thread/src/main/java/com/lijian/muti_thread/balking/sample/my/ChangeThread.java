package com.lijian.muti_thread.balking.sample.my;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@lombok.Data
public class ChangeThread implements Runnable {


    private Data data;
    private String threadName;

    public ChangeThread(Data data, String threadName) {
        this.data = data;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            data.change("change file " + i);
            try {
//                显示保存
                data.save();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100L);

            }
 catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
