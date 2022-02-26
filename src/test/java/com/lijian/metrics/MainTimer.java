package com.lijian.metrics;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Timer;
import com.yammer.metrics.core.TimerContext;
import com.yammer.metrics.reporting.ConsoleReporter;
import com.yammer.metrics.reporting.CsvReporter;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainTimer {

    private static Timer timer = Metrics.newTimer(MainTimer.class, "TestTimer", TimeUnit.MILLISECONDS, TimeUnit.SECONDS);

    public static void main(String[] args) throws InterruptedException, IOException {
//        ConsoleReporter.enable(1, TimeUnit.SECONDS);
       String  catalina_home = System.getProperty("user.dir");
        File file = new File(catalina_home+System.getProperty("file.separator")+"csv_reporter");
//        File file = new File(catalina_home);

        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        if (!file.exists()){
            file.mkdirs();
        }
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        CsvReporter.enable(file, 5, TimeUnit.SECONDS);
        Random rn = new Random();
        timer.time();
        System.out.println();
        while (true){
            TimerContext context = timer.time();
            Thread.sleep(rn.nextInt(1000));
            context.stop();
        }
    }
}
