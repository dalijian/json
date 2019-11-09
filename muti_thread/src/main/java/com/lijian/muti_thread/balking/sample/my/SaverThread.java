package com.lijian.muti_thread.balking.sample.my;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@lombok.Data
public class SaverThread implements Runnable {

    private Data data ;
    private String threadName;

    public SaverThread(Data logger, String threadName) {
        this.data = logger;
        this.threadName = threadName;
    }

    public SaverThread(Data logger) {
        this.data = logger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                data.save();
                TimeUnit.SECONDS.sleep(1L);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
