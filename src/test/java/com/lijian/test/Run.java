package com.lijian.test;

public class Run {
    public static void main(String[] args) {
        Threadone threadone =  new Threadone();
        Threadtwo threadtwo = new Threadtwo();
        for (int i = 0; i < 20; i++) {
             new Thread(threadone).start();
//             new Threa(threadtwo).start();
        }
//        new Threa(threadtwo).start();
    }
}
