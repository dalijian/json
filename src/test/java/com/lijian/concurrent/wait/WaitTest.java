package com.lijian.concurrent.wait;

public class WaitTest {

    /**
     *   ThreadA ,ThreadB 不应该 在 方法 内 循环 ， 应该 循环 执行 方法
     * @param args
     */
    public static void main(String[] args) {
        Resource_2 resource = new Resource_2();

        Thread threadA = new Thread(() -> {
            while (true) {
                resource.add();
            }


        });
        Thread threadB = new Thread(() -> {
            while (true) {
                resource.sub();
            }

        });

        Thread threadC = new Thread(() -> {
            while (true) {
                resource.add();
            }


        });
        Thread threadD = new Thread(() -> {
            while (true) {
                resource.sub();
            }

        });
//        Thread threadA = new Thread(() -> {
//
//                resource.add();
//
//
//
//        });
//        Thread threadB = new Thread(() -> {
//
//                resource.sub();
//
//
//        });
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
