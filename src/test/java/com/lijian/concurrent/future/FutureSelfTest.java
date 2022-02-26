package com.lijian.concurrent.future;

public class FutureSelfTest {
    public static void main(String[] args) {
        FutureSelf futureSelf = new FutureSelf(() -> FutureSelfTest.class.getInterfaces().toString());
        Thread thread = new Thread(futureSelf);
        thread.start();
        Object name = futureSelf.get();
        System.out.println(name);

    }
}
