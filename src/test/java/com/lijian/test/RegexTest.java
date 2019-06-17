package com.lijian.test;

import org.junit.Test;

import java.util.regex.Pattern;

public class RegexTest {
    @Test
    public void testMatch(){
//        Pattern p = Pattern.compile(regex);
        System.out.println("123d12".matches("\\d*"));
    }
}
