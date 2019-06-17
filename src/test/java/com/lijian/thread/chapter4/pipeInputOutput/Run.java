package com.lijian.thread.chapter4.pipeInputOutput;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {

        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();

        outputStream.connect(inputStream);

        ThreadRead threadRead  = new ThreadRead(readData,inputStream);
        threadRead.start();

        Thread.sleep(2000);
        ThreadWrite threadWrite = new ThreadWrite(writeData,outputStream);
        threadWrite.start();
    }
}
