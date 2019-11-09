package com.lijian.algorithms.recusion;

import org.junit.Test;

public class Fibonacci {
//    0、1、1、2、3、5、8、13、21、34、55、89、144
    @Test
    public int fibonacciTest(int x) {

        if (x <= 1) {
            return x;
        } else {
            return fibonacciTest(x - 1) + fibonacciTest(x - 2);
        }
    }
}
