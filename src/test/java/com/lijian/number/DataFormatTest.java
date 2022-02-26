package com.lijian.number;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class DataFormatTest {

    static DecimalFormat decimalFormat = new DecimalFormat("0000");
    public static void main(String[] args) {
        System.out.println(decimalFormat.format(99));
        int a =1;
        int finalA = a;
        Thread thread = new Thread(()-> {
            try {

                System.out.println(finalA);
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("application is stop");
        });
        a++;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(Thread.currentThread().getName())));
        Runtime.getRuntime().addShutdownHook(thread);

//        Signal sg = new Signal("TERM"); // kill -15 pid
//        Signal.handle(sg, new SignalHandler() {
//            @Override
//            public void handle(Signal signal) {
//                System.out.println("signal handle: " + signal.getName());
//                // 监听信号量，通过System.exit(0)正常关闭JVM，触发关闭钩子执行收尾工作
//                System.exit(0);
//            }
//        });

    }
}

