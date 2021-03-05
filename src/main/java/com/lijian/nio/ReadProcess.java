package com.lijian.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class ReadProcess {

    public static void main(String[] arg) throws Exception {
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;

        try {
//            Thread.sleep(10000);

            randomAccessFile = new RandomAccessFile("test.txt", "rw");
            channel = randomAccessFile.getChannel();
            FileLock lock = null;

            while (true) {
                lock = channel.tryLock();

                if (null == lock) {
                    System.out.println("Read Process : get lock failed");
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }

            System.out.println("Read Process : get lock");

            System.out.println("Read Process : get " + randomAccessFile.length() + " numbers");

           byte [] bytes =   new byte[new Long(randomAccessFile.length()).intValue()];

            randomAccessFile.read(bytes);
            System.out.println("content:");
            System.out.println( new String(bytes));
            lock.release();
            System.out.println("Read Process : release lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != randomAccessFile) {
                randomAccessFile.close();
            }
            if (null != channel) {
                channel.close();
            }
        }
    }
}
