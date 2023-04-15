package com.lijian.concurrent.lock;

import java.util.Vector;
import java.util.concurrent.*;

public class CountDownLatchTest {
    public static void main(String[] args) {


        while (存在未对账订单) {
            Thread t1 = new Thread(() -> {
                pos = getPOrders();

            });

            Thread t2 = new Thread(() -> {
                dos = getDOrders();
            });
            t1.join();
            t2.join();
            diff = check(pos, dos);
            save(diff);
        }

        Executor executor = Executors.newFixedThreadPool(2);
        while (存在未对账订单) {
            CountDownLatch countDownLatch = new CountDownLatch(2);

            executor.execute(() -> {
                pos = getPOrders();
                countDownLatch.countDown();
            });

            executor.execute(() -> {
                dos = getDOrders();
                countDownLatch.countDown();
            });
            countDownLatch.await();
            diff = check(pos, dos);
            save(diff);
        }


        Vector<P> pos;
        Vector<D> dos;
        //它要做的事情是，让一
        //组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会
        //开门，所有被屏障拦截的线程才会继续运行
        ExecutorService executors = Executors.newFixedThreadPool(1);
        final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            executor.execute(() -> check());
        });

        void check () {
            P p = pos.remove(0);
            D d = dos.remove(0);

            diff = check(p, d);
            save(diff);
        }
        void checkAll () {
            Thread t1 = new Thread(() -> {
                while (存在未对账订单) {
                    pos.add(getPOrders());
                    barrier.await();

                }
            });

            t1.start();
            Thread t2 = new Thread(() -> {
                while (存在未对账订单) {
                    dos.add(getPOrders());
                    barrier.await();

                }
            });
            t2.start();


        }
    }
}
