package com.lijian.thread.chapter4.pipeInputOutput;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadRead extends Thread {
    private ReadData readData;
    public PipedInputStream inputStream;

    public ThreadRead(ReadData writeData, PipedInputStream outputStream) {
        super();
        this.readData = writeData;
        this.inputStream = outputStream;
    }

    public ThreadRead(ReadData readData) {
    }

    @Override
    public void run(){
        readData.readMethod(inputStream);
        
    }

}
