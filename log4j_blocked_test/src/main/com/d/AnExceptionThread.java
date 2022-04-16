package com.d;

import com.b.AnException;
import org.apache.log4j.Logger;

public class AnExceptionThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AnExceptionThread.class);
    
    public AnExceptionThread(String threadName) {
        super(threadName);
    }
    
    @Override
    public void run() {
        LOGGER.info("Just logging INFO in AnExceptionThread", new AnException("test exception", new AnException("cause exception")));
    }
}