package com.lijian.thread.chapter5.condition123;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Run {
    volatile private static int nextPrintWho =1;
    private static ReentrantLock lock = new ReentrantLock();
    final private static Condition conditionA = lock.newCondition();
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    conditionA.await();

                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("threadA" + (i + 1));

                }
                nextPrintWho = 2;
                conditionB.signalAll(); //唤醒conditionB


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    conditionB.await();
                    System.out.println("conditionB will sleep");
                    Thread.currentThread().sleep(5000);

                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("threadB" + (i + 1));

                }
                nextPrintWho = 3;
                conditionC.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    conditionC.await();

                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("threadC" + (i + 1));

                }
                nextPrintWho = 1;
                conditionA.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread[] aarrays = new Thread[5];
        Thread[] barrays = new Thread[5];
        Thread[] carrays = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aarrays[i] = new Thread(threadA);
            barrays[i] = new Thread(threadB);
            carrays[i] = new Thread(threadC);
            aarrays[i].start();
            barrays[i].start();
            carrays[i].start();
        }
    }
    }