package com.lijian.concurrent.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        String A = "银行流水A";
                        try {
                            exgr.exchange(A);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";
                String A = null;
                try {
                    A = exgr.exchange("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A 和B 数据是否 一致" + A.equals(B) + "A录入的是：" + A + ",B录入是：" + B);

            }
        });
        threadPool.shutdown();
    }
}
