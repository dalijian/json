package com.lijian.concurrent.interept;

import com.lijian.thread.deathLock.DeathLock;

public class InterruptBlockedTest {


        /**
         * 处于 blocked 中 不能响应 中断
         * @param args
         */
        public static void main(String[] args) {
            DeathLock deathLock = new DeathLock();
            Thread threadA = new Thread(() -> deathLock.getA());
            Thread threadB = new Thread(() -> deathLock.getB());
            threadA.start();
            threadB.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadA.interrupt();
        }

}
