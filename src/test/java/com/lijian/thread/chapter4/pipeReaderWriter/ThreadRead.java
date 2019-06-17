package com.lijian.thread.chapter4.pipeReaderWriter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;

public class ThreadRead extends Thread {
    private ReadData readData;
    public PipedReader inputStream;

    public ThreadRead(ReadData writeData, PipedReader outputStream) {
        super();
        this.readData = writeData;
        this.inputStream = outputStream;
    }

    public ThreadRead(ReadData readData) {
    }

    @Override
    public void run(){
        try {
            readData.readMethod(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
