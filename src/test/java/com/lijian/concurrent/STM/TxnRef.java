//package com.lijian.concurrent.STM;
//
//public class TxnRef<T> {
//
//    volatile VersionRef curRef;
//
//    public TxnRef(T value) {
//        this.curRef =new VersionRef(value,0L) ;
//    }
//
//    public T getValue(Txn txn) {
//        return txn.get(this);
//    }
//
//    public void setValue(T value, Txn txn) {
//        txn.set(this, value);
//    }
//}
