package com.lijian.system;

import org.junit.Test;

public class SystemTest {


    @Test
    public void testSystem(){
        System.out.println(System.getSecurityManager());
    }
    @Test
    public void testSystemProperties(){
        System.out.println(System.getProperty("java.io.tmpdir"));

    }
    @Test
    public void testDouble(){
        System.out.println(0==new Double(0));
        System.out.println(0 == Double.valueOf(String.valueOf(new Double(0))).intValue());

    }
}
