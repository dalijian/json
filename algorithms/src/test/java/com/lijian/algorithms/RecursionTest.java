package com.lijian.algorithms;

import org.junit.Test;

import java.util.Stack;

//递归 主要 要 找到 f(n) 与 f(n-1)  之间的关系
public class RecursionTest {

    //    1 到 100 总和 使用 for 循环实现
    @Test
    public void test() {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        System.out.println(sum
        );
    }

    @Test
    public void stack(){

        Stack<Integer> stack = new Stack<>();
        int total =0;
        int step = 100;
        Integer value ;
        while (step >= 0) {
            stack.push(step);
            step--;
        }
        while ((value =stack.pop() )!= null) {
            total+= value;
        }
        System.out.println("total==" + total);
    }

    //递归求1 到 100 总和
    @Test
    public void resursionTest() {
        int result = recurion(100);
        System.out.println(result);

    }

    public int recurion(int i) {
        if (i == 1) {
            return 1;
        }
        return i + recurion(i - 1);

    }


    @Test
    public void febonacciTest() {


        int result = febonacci(2);
        System.out.println(result);
    }

    public int febonacci(int i) {

        if (i == 1) {
            return 1;
        }
        if (i <= 0) {
            return 0;
        }
        return febonacci(i - 1) + febonacci(i - 2);
    }
}
