package com.lijian.test;

import org.junit.Test;

public class WhileTest {


    @Test
    public void whileTest(){


      int  value =  WhileValue(1, 1);
        System.out.println(value);
    }

    private int WhileValue(int i, int i1) {
        while (true) {
            return i+i1;
        }
    }
}
