package com.lijian.thread.chapter4.pipeInputOutput;

import java.io.PipedOutputStream;

public class ThreadWrite extends Thread {
    private WriteData writeData;
    public PipedOutputStream outputStream;

    public ThreadWrite(WriteData writeData, PipedOutputStream outputStream) {
        super();
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    public ThreadWrite(WriteData writeData) {

    }

    @Override
    public void run(){
        writeData.writeMethod(outputStream);

    }

}
