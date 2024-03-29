package com.c;

import com.a.AnObject;
import org.apache.log4j.Logger;

public class AnObjectThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AnObjectThread.class);
    
    public AnObjectThread(String threadName) {
        super(threadName);
    }
    
    @Override
    public void run() {
        AnObject anObject = new AnObject("Object created in AnObjectThread");
        LOGGER.info(anObject);
    }
}