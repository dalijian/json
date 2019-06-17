package com.lijian.concurrent.chapter5;

public class LockUseCase {
    public static void main(String[] args) {
      int cupNum =  Runtime.getRuntime().availableProcessors(); //cpu数量
        System.out.println(cupNum);
    }
}
