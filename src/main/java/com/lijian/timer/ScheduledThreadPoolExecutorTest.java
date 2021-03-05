package com.lijian.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest
{
    static ScheduledThreadPoolExecutor stp = null;
    static int index =2;
    
    private static String getTimes() {  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");  
        Date date = new Date();  
        date.setTime(System.currentTimeMillis());  
        return format.format(date);  
    } 
    
    
    private static class MyTask implements Runnable {  
        
        @Override  
        public void run() {
            try {
                index--;
                System.out.println("2= " + getTimes() + " " + index);
                System.out.println(2 / index);
//            if(index >=10){  
//                stp.shutdown();  
//                if(stp.isShutdown()){  
//                    System.out.println("停止了？？？？");
//                } 
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    public static void main(String[] args)  {
//        stp = new ScheduledThreadPoolExecutor(5);
//        MyTask mytask = new MyTask();
//        //mytask为线程，2是首次执行的延迟时间，最后一个参数为时间单位
////        stp.schedule(mytask, 2, TimeUnit.SECONDS);
//        // 首次执行延迟2秒，之后的执行周期是1秒
////        stp.scheduleAtFixedRate(mytask, 2, 1,TimeUnit.SECONDS );
//        //首次执行延迟2秒，之后从上一次任务结束到下一次任务开始时1秒
////        ScheduledFuture<?> future = stp.scheduleWithFixedDelay(mytask, 2, 1, TimeUnit.SECONDS);
//        stp.schedule(() -> {
//            System.out.println(Thread.currentThread().getName() + " stop");
//        }, 5, TimeUnit.SECONDS);
////        long time = future.getDelay(TimeUnit.SECONDS);
////        System.out.println("time:" + time);
//
//        //当前线程 标记 为  中断 后 ，后续地 sleep(),system.exit() 都 将 无效， 当前 线程 会 等待 子线程  运行 结束 后 停止 运行
//
//
////        Thread.currentThread().interrupt();
//        if (! stp.isShutdown()) {
//            System.out.println("stp is not  shutdown");
//            stp.shutdown();
//            System.out.println("stp shutdown ");
//        }
//        if (Thread.currentThread().isInterrupted()) {
//            System.out.println(Thread.currentThread().getName() + " is Interrupted");
//        }
//        if (Thread.currentThread().isDaemon()) {
//            System.out.println(Thread.currentThread().getName() + " is daemon");
//        }
////        System.exit(0);
//
//        if (Thread.currentThread().isAlive()) {
//            System.out.println(Thread.currentThread().getName() + " is alive");
//        }
//    }

//         线程池不关闭 主线程 是 无发 关闭地
    public static void main(String[] args) {
        stp = new ScheduledThreadPoolExecutor(5);
        stp.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
        }, 2, TimeUnit.SECONDS);
        stp.shutdown(); // 手动 关闭 线程 池
    }

}