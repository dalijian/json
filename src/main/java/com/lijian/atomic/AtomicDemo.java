package com.lijian.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class AtomicDemo {



    public static void main(String[] args) {

         AtomicInteger atomicInteger = new AtomicInteger();
        int result = atomicInteger.updateAndGet(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand;
            }
        });
        System.out.println(result);

    }
}
