package com.lijian.concurrent.demo;

import java.util.ArrayList;
import java.util.List;

//解决死锁 问题： 破坏 占用且等待条件
public class Allocator {
    private List<Object> als = new ArrayList<>() ;

//    申请全部资源
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        }else{
            als.add(from);
            als.add(to);
        }
        return true;
    }
//释放全部资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to
        );
    }
}

class Account {
//    allocator 应该为单例
    private Allocator allocator;
    private int balance;

    void transfer(Account target, int amt) {
//        异常性申请转出账户和转入账户，直到成功
        while (!allocator.apply(this, target)) {

        }
        try{

//        锁定转出账户
            synchronized (this) {

//锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance-=amt;
                        target.balance+=amt;
                    }
                }
            }
        }finally {
            allocator.free(this, target);
        }
    }
}

//2. 破坏不可抢占条件
//使用 java.util.concurrent   lock.trylock

//3. 破坏 循环等待条件
//人工排序
class Account2{
    private int id;
    private int balance;

    //    转账
    void transfer(Account2 target, int amt) {
        Account2 left= this;
        Account2 right=target;
        if (this.id > target.id) {
            left=target;
            right=this;
        }

        synchronized (left) {
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance-=amt;
                    target.balance+=amt;
                }
            }
        }
    }
}


//使用等待-通知机制
class Allocator2{
    private List<Object> als;

    synchronized void apply(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        als.add(from);
        als.add(to);
    }


    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();

    }
}