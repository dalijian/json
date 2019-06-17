package com.lijian.concurrent.memorizer;

public interface Computable<A,V> {
    V compute(A arg) throws  InterruptedException;

}
