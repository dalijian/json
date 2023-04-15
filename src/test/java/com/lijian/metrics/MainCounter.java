//package com.lijian.metrics;
//
//import com.yammer.metrics.Metrics;
//import com.yammer.metrics.core.Counter;
//import com.yammer.metrics.reporting.ConsoleReporter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class MainCounter {
//
//
//    Counter counter = Metrics.newCounter(MainCounter.class, "testCounter");
//
//    final List<String> list=new ArrayList<>();
//
//    void add(String string) {
//
//        counter.inc();
//        list.add(string);
//
//    }
//
//    String take(){
//
//        counter.dec();
//        return list.remove(0);
//
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        MainCounter mainCounter =new MainCounter();
//        ConsoleReporter.enable(5,TimeUnit.SECONDS);
//
//        while (true) {
//            mainCounter.add("S");
//            Thread.sleep(1000);
//
//        }
//    }
//}
