package com.lijian.algorithms;

import org.junit.Test;

//递归 主要 要 找到 f(n) 与 f(n-1)  之间的关系
public class RecursionTest {

//    1 到 100 总和
    @Test
    public void test(){
 int sum    =0;
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        System.out.println(sum
        );
    }
    //递归求1 到 100 总和
    @Test
    public void resursionTest(){
        int result = recurion(100);
        System.out.println(result);

    }
    public int  recurion(int i) {
        if (i == 1) {
            return 1;
        }
        return i+recurion(i-1);

    }


    @Test
    public void febonacciTest(){


        int result = febonacci(2);
        System.out.println(result);
    }
    public int febonacci(int i){

        if (i == 1) {
            return 1;
        }
        if (i <= 0) {
            return 0;
        }
        return febonacci(i - 1) + febonacci(i - 2);
    }
}
