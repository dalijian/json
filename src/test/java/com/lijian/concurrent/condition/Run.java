package com.lijian.concurrent.condition;

import java.util.stream.LongStream;

public class Run {

    public static void main(String[] args) {
        FunctionCondition functionCondition = new FunctionCondition();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(functionCondition);
            thread.start();
        }

    }
}
