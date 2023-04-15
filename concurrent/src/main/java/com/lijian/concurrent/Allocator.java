package com.lijian.concurrent;

import java.util.ArrayList;
import java.util.List;

class Allocator {

    private List<Object> als = new ArrayList();

    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);

    }
}

class Account {
    private Allocator actr;
    private int balance;

    public Account(Allocator actr, int balance) {
        this.actr = actr;
        this.balance = balance;
    }

    void transfer(Account target, int amount) {
        while (!actr.apply(this, target)) {
            try {
                synchronized (this) {
                    synchronized (target) {
                        if (this.balance > amount) {
                            this.balance -= amount;
                            target.balance += amount;
                            System.out.println(Thread.currentThread());
                            System.out.println(123);
                        }
                    }
                }
            } finally {
                actr.free(this, target);
            }
        }
    }

    public Allocator getActr() {
        return actr;
    }

    public void setActr(Allocator actr) {
        this.actr = actr;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}


