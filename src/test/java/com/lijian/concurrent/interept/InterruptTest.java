package com.lijian.concurrent.interept;

public class InterruptTest {

    class Proxy {
        //线程终止标识位
        volatile boolean terminated = false;

        boolean started = false;
        //采集线程
        Thread rptThread;

        synchronized void start() {
//不允许同时启动多个采集线程
            if (started) {
                return;
            }

            started = true;

            terminated = false;

            rptThread = new Thread(() -> {
                while (!terminated) {
//                    省略采集，回传实现
                    report();
//                        每隔两秒采集，回传一次数据
                    try {
                        Thread.sleep(2000);


                    } catch (InterruptedException e) {

//                        重新设置线程中断状态
                        Thread.currentThread().interrupt();

                    }
                }
//                执行到此处线程马上终止
                started = false;
            });
            rptThread.start();
        }

        private void report() {
        }

        //终止采集功能
        synchronized void stop() {

//            设置中断标志位
            terminated = true;
//            中断线程
            rptThread.interrupt();
        }
    }
}
