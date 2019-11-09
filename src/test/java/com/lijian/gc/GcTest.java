package com.lijian.gc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class GcTest {

    @Test
    public void test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(15);
        byte[] placeHolder = new byte[1024*1024*400];
        TimeUnit.SECONDS.sleep(5);
        placeHolder=null;
        TimeUnit.SECONDS.sleep(20);

    }

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(15);
        byte[] placeHolder = new byte[1024*1024*1000];
        TimeUnit.SECONDS.sleep(10);
        placeHolder=null;
       System.gc();

        TimeUnit.SECONDS.sleep(30);
    }
}
