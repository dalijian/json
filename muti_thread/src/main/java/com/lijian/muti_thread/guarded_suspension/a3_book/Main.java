package com.lijian.muti_thread.guarded_suspension.a3_book;

import java.util.concurrent.TimeUnit;

public class Main {
//    public static void main(String[] args) {
//        // 启动线程
//        RequestQueue requestQueue = new RequestQueue();
//        Thread alice = new ClientThread(requestQueue, "Alice", 314159L);
//        Thread bobby = new ServerThread(requestQueue, "Bobby", 265358L);
//        alice.start();
//        bobby.start();
//
//        // 等待约10秒
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//        }
//
//        // 调用interrupt方法
//        System.out.println("***** calling interrupt *****");
//        alice.interrupt();
//        bobby.interrupt();
//    }
public static void main(String[] args) {
  Thread thread =  new Thread(()->{
      while (true) {
          try {
              TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
              e.printStackTrace();
//              线程捕捉了异常后 将 interrupt 设置成 false ,所以 在 之后的循环中 不会 捕获异常
//                      需要 手动设置中断异常
//              Thread.currentThread().interrupt();

          }
          System.out.println(Thread.currentThread().getName());

      }


    });
  thread.start();
    thread.interrupt();



}
}
