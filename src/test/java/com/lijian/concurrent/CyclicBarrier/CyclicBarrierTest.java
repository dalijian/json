package com.lijian.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  cyclicBarrier 为0时 自动 重制 为 初始值
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2,() -> System.out.println("cyclic barrier is end")); // 当 parrties 为3 时 由于 没有第三个线程 所以之前两个线程会一直blocked

    public static void main(String[] args) {
        while (true) {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(1);
                }
            }).start();

            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(2);

        }
    }
}
