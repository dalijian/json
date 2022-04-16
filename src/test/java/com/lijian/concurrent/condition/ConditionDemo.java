package com.lijian.concurrent.condition;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 condition List 可以 唤醒 指定 condition 关联 的 线程
 */
public class ConditionDemo {

    List<Rule> conditionList = new CopyOnWriteArrayList<>();
    Lock lock = new ReentrantLock();
    AtomicInteger atomicInteger = new AtomicInteger(10);
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    void get() {
        lock.lock();
        try {
            Condition condition = lock.newCondition();
            conditionList.add(new Rule(condition, threadLocal.get(),Thread.currentThread().getName()));
            while (atomicInteger.get() <= 10) {
                System.out.println(Thread.currentThread().getName() + " wait");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " wake up");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void set(int index) {
        lock.lock();
        try {
            System.out.println(conditionList);
            System.out.println(" wait size " + conditionList.size());
            for (int i = 0; i < conditionList.size(); i++) {

                if (conditionList.get(i).flag == 1) {
                    conditionList.get(i).condition.signal();
                    break;
                }
            }
            atomicInteger.incrementAndGet();
        } finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) {

        ConditionDemo conditionDemo = new ConditionDemo();
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                Thread thread = new Thread(() -> {
                    conditionDemo.threadLocal.set(1);
                    conditionDemo.get();

                });
                thread.setPriority(10);
                thread.start();
            } else {
                Thread thread = new Thread(() -> conditionDemo.get());
                thread.start();
            }
        }
        conditionDemo.set(1);

    }

    class Rule {
        public Condition condition;
        public int flag;
        public String name ;


        public Rule(Condition condition, int flag, String name) {
            this.condition = condition;
            this.flag = flag;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "condition=" + condition +
                    ", flag=" + flag +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
