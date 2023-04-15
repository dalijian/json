package com.lijian.concurrent;

import org.junit.Test;

public class SimpleTest {
    @Test
    public void testAllocator() {
        Allocator allocator = new Allocator();
        Account accountA = new Account(allocator, 100);
        Account accountB = new Account(allocator, 100);
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                accountA.transfer(accountB, 1);
            }).start();

        }
        System.out.println(accountA.getBalance());
        System.out.println(accountB.getBalance());
    }
}
