package com.lijian.test;

public class Test {
    private static  long count =0;
    private void add10k(){
    int idx =0;
        while (idx < 1000) {
            count+=1;

        }}


        public static long calc() throws InterruptedException {
        final Test test = new Test();
        Thread thread1 = new Thread(()->{
            test.add10k();
        });
            Thread thread2 = new Thread(()->{
                test.add10k();
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            return  count;
        }

    public static void main(String[] args) throws InterruptedException {
        long num = calc();
        System.out.println(num);
    }

        @org.junit.jupiter.api.Test
    public void tees(){
            System.out.println(Integer.valueOf("00101"));
        }

}
