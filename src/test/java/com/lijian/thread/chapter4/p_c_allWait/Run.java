package com.lijian.thread.chapter4.p_c_allWait;

public class Run {

    public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] threadPS = new ThreadP[2];
        ThreadC[] tHreadCS= new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            threadPS[i]= new ThreadP(p);
            threadPS[i].setName("生产者"+(i+1));
            tHreadCS[i] =new ThreadC(c);
            tHreadCS[i].setName("消费者"+(i+1));
            tHreadCS[i].start();
            threadPS[i].start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
            Thread.currentThread().getThreadGroup().enumerate(threadArray);
            for (int j = 0; j < threadArray.length; j++) {
                System.out.println(threadArray[i].getName()+""+threadArray[i].getState());

            }

        }
    }
}
