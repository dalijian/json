package com.lijian.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

/**
 * SystemUtil
 * 系统工具类
 *
 * @author hengyumo
 * @version 1.0
 * @since 2020/2/4
 */
public class SystemUtil_2 {

    private final static int BUFFER_SIZE = 1024;

    private final static String DEFAULT_ENCODING = "gbk";

    private static class ProcessWorker extends Thread {
        private final Process process;
        private volatile int exitCode = -99;
        private volatile boolean completed = false;
        private volatile String output = "";

        private ProcessWorker(Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            try (InputStreamReader reader = new InputStreamReader(
                    process.getInputStream(), DEFAULT_ENCODING)) {

                StringBuilder log = new StringBuilder();
                char[] buffer = new char[BUFFER_SIZE];
                int length;
                while ((length = reader.read(buffer)) != -1) {
                    log.append(buffer, 0, length);
                }
                output = log.toString();
                exitCode = process.waitFor();
                completed = true;
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        }

        public int getExitCode() {
            return exitCode;
        }

        public String getOutput() {
            return output;
        }

        public boolean isCompleted() {
            return completed;
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        StringBuilder stringBuilder = new StringBuilder();
        int result = execCmd("nbtstat -A " + "192.168.1.200", stringBuilder, 50);

        System.out.println(result);
        System.out.println(stringBuilder.toString());
    }
    public static int execCmd(String command, StringBuilder log, int timeoutSecond) throws IOException, TimeoutException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        // 合并错误输出流
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        ProcessWorker processWorker = new ProcessWorker(process);
        int exitCode = processWorker.getExitCode();
        processWorker.start();
        try {
            processWorker.join(timeoutSecond * 1000);
            if (processWorker.isCompleted()) {
                log.append(processWorker.getOutput());
                exitCode = processWorker.getExitCode();
            } else {
                process.destroy();
                processWorker.interrupt();
                throw new TimeoutException("进程执行时间超时");
            }
        } catch (InterruptedException e) {
            processWorker.interrupt();
        }
        return exitCode;
    }

    public static String getWindowsMACAddress(String ip) {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * windows下的命令，显示信息中包含有mac地址信息
             */
            // process = Runtime.getRuntime().exec("ipconfig /all");
            process = Runtime.getRuntime().exec("nbtstat -A " + ip);
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), "GBK")); // windows系统都是GBK编码，不加GBK读出的中文是乱码
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * 寻找标示字符串[physical address]
                 */
                index = line.toLowerCase().indexOf("mac 地址 =");
                System.out.println(line);
                if (index != -1) {
                    index = line.indexOf("=");
                    if (index != -1) {
                        /**
                         * 取出mac地址并去除2边空格
                         */
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }
}

