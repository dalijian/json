package com.lijian.concurrent.fork_join;

public class RecursiveTest {
    public static void main(String[] args) {
        RecursiveTest recursiveTest = new RecursiveTest();
        int result =recursiveTest.fibonacci(5);
        System.out.println(result);
    }


    public int fibonacci(int i) {

        if (i <= 1) {
            return i;
        }else{

            return fibonacci(i - 1) + fibonacci(i - 2);
        }


    }
//    def fib(n):
//    a, b = 1, 1
//            while a < n:
//    print(a, end=' ')
//    a, b = b, a+b
//    print("")fib(100) #输出的是100以内的斐波那契数列
}
