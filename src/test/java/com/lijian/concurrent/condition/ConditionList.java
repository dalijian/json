//package com.lijian.concurrent.condition;
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Deque;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class ConditionList {
//
//    ReentrantLock lock = new ReentrantLock();
//    Deque conditionList =new ArrayDeque<Condition>();
//
//    AtomicInteger atomicInteger = new AtomicInteger(0);
//
//
//    void addT() {
//        lock.lock();
//        try {
//            if (atomicInteger.get() > 0) {
//                atomicInteger.getAndUpdate(operand -> operand + 5);
//            }else{
//                Condition condition = lock.newCondition();
//                conditionList.addLast(condition);
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    conditionList.remove(condition);
//                }
//            }
//            System.out.println(atomicInteger.incrementAndGet());
//        } finally {
//            if (lock.isHeldByCurrentThread())
//                lock.unlock();
//        }
//    }
//
//    void subT() {
//        lock.lock();
//        try {
//            while (atomicInteger.get() < 5) {
//                try {
//                    conditionSub.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println(atomicInteger.decrementAndGet());
//            conditionAdd.signalAll();
//        } finally {
//            if (lock.isHeldByCurrentThread())
//                lock.unlock();
//        }
//    }
//
//}
