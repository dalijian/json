//package com.lijian.concurrent.STM;
//
//import org.multiverse.api.StmUtils;
//
//public class UnsafeAccount {
//    private long balance;
//
//    public UnsafeAccount(long balance) {
//        this.balance = balance;
//    }
//
//    void transfer(UnsafeAccount target, long amt) {
//        if (this.balance > amt) {
//            this.balance-=amt;
//            target.balance+=amt;
//        }
//    }
//}
// class Account {
//    private TxnLong balance;
//
//    public Account(long balance) {
//        this.balance = StmUtils.newTxnLong(balance);
//    }
//
//    void transfer(UnsafeAccount target, long amt) {
//        atomic(()->{
//            if (this.balance.get()> amt) {
//                this.balance.decrement(amt);
//                target.balance.increment(amt);
//            }
//        });
//    }
//}