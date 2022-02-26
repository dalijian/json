package com.lijian.concurrent.volatile_;

public class SafeCalcTest {
    public static void main(String[] args) {
        SafeCalc safeCalc = new SafeCalc();
        SafeCalcRunnable safeCalcRunnable = new SafeCalcRunnable(safeCalc);
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(safeCalcRunnable);
            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
    class SafeCalcRunnable implements  Runnable{
        SafeCalc safeCalc;

        public SafeCalcRunnable(SafeCalc safeCalc) {
            this.safeCalc = safeCalc;
        }

        @Override
        public  void run() {
            long result = safeCalc.get();
            System.out.println(result);
            safeCalc.addOne();
        }

}
