//package com.lijian.metrics;
//
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.TimeUnit;
//
//public class MetricsSLF4jReporter {
//    public static void main(String[] args) {
//        final Slf4jReporter reporter = Slf4jReporter.forRegistry(registry)
//                .outputTo(LoggerFactory.getLogger("com.example.metrics"))
//                .convertRatesTo(TimeUnit.SECONDS)
//                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .build();
//        reporter.start(1, TimeUnit.MINUTES);
//    }
//}
