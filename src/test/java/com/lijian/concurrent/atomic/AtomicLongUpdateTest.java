package com.lijian.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicLongUpdateTest {
    public static void main(String[] args) {

        AtomicLongFieldUpdater<Apple> atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(Apple.class, "price");

        long result = atomicLongFieldUpdater.accumulateAndGet(new Apple(2L), 10, (left, right) -> right);
        System.out.println(result);
    }
}

class Apple{
    public volatile long price;

    public Apple(long l) {
        this.price = l;
    }
}