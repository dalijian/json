package com.lijian.thread.chapter4.pipeReaderWriter;

import java.io.PipedOutputStream;
import java.io.PipedWriter;

public class ThreadWrite extends Thread {
    private WriteData writeData;
    public PipedWriter outputStream;

    public ThreadWrite(WriteData writeData, PipedWriter outputStream) {
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
