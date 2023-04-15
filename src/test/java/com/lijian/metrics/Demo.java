//package com.lijian.metrics;
//
//import com.yammer.metrics.Metrics;
//import com.yammer.metrics.core.Gauge;
//import com.yammer.metrics.core.Meter;
//import com.yammer.metrics.core.Metric;
//import com.yammer.metrics.reporting.ConsoleReporter;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class Demo {
//
//    @Test
//    public void gaugeTest() throws InterruptedException {
//
//        final List<String> list=new ArrayList<>();
//        ConsoleReporter.enable(5,TimeUnit.SECONDS);
//
//        Gauge<Integer> gauge = Metrics.newGauge(Demo.class,"test gauge",new Gauge(){
//
//            @Override
//            public Object value() {
//                return list.size();
//            }
//
//        });
//
//        while (true) {
//            list.add("s");
//            Thread.sleep(1000);
//
//        }
//    }
//
//
//
//    @Test
//    public void MeterTest() throws InterruptedException {
//
//        final List<String> list=new ArrayList<>();
//        ConsoleReporter.enable(5,TimeUnit.SECONDS);
//
//        Meter meter=Metrics.newMeter(Demo.class,"meter","request",TimeUnit.SECONDS);
//
//
//        while (true) {
//            meter.mark();
//            Thread.sleep(1000);
//
//        }
//    }
//
//
//}
