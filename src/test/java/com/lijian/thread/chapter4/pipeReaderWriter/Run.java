package com.lijian.thread.chapter4.pipeReaderWriter;

import java.io.*;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {

        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedWriter outputStream = new PipedWriter();
        PipedReader inputStream = new PipedReader();

        outputStream.connect(inputStream);

        ThreadRead threadRead  = new ThreadRead(readData,inputStream);
        threadRead.start();

        Thread.sleep(2000);
        ThreadWrite threadWrite = new ThreadWrite(writeData,outputStream);
        threadWrite.start();
    }
}
