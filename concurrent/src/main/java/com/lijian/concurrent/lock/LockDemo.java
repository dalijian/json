package com.lijian.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(100);
        account2.setBalance(50);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                account1.transfer(account2, 1);
                account2.transfer(account1, 2);

            }).start();

        }
    }

}

class Account {
    private int balance;
    private final Lock lock = new ReentrantLock();

    //转账
    void transfer(Account tar, int amt) {
        while (true) {      //tryLock() 尝试非阻塞的获取锁，调用该方法后立即返回，如果能够获取则返回true，否则返回false ，返回false 释放当前锁
            if (this.lock.tryLock()) {
                try {
                    try {
                        Thread.sleep(12000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}