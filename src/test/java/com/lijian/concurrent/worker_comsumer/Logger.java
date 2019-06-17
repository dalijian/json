package com.lijian.concurrent.worker_comsumer;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Logger {

    final BlockingQueue<LogMsg> blockingQueue = new ArrayBlockingQueue<LogMsg>(500);

    static final int batchSize = 500;

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    void start() throws IOException {
        File file = File.createTempFile("foo", ".log");
        final FileWriter writer = new FileWriter(file);

        this.executorService.execute(() -> {
            try {
                int curIdx = 0;

                long perFT = System.currentTimeMillis();

                while (true) {
                    LogMsg log = blockingQueue.poll(5, TimeUnit.SECONDS);
                    if (log != null) {
                        writer.write(log.toString());
                        ++curIdx;
                    }
                    if (curIdx <= 0) {
                        continue;
                    }
                    if (log != null && log.level == LEVEL.ERROR || curIdx == batchSize || System.currentTimeMillis() - perFT > 5000) {
                        writer.flush();
                        curIdx = 0;
                        perFT = System.currentTimeMillis();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void info(String msg) throws InterruptedException {
        blockingQueue.put(new LogMsg(LEVEL.INFO, msg));

    }

    void error(String msg) throws InterruptedException {
        blockingQueue.put(new LogMsg(LEVEL.ERROR, msg));

    }

}

enum LEVEL {
    INFO, ERROR
}

class LogMsg {
    LEVEL level;
    String msg;

    public LogMsg(LEVEL level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LogMsg{" +
                "level=" + level +
                ", msg='" + msg + '\'' +
                '}';
    }
}
