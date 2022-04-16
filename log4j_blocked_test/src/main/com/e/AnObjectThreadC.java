package com.e;

import com.a.AnObject;
import org.apache.log4j.Logger;

public class AnObjectThreadC extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AnObjectThreadC.class);

    public AnObjectThreadC(String threadName) {
        super(threadName);
    }
    
    @Override
    public void run() {
        System.out.println(LOGGER);
        AnObject anObject = new AnObject("Object created in AnObjectThread-C");
        LOGGER.info(anObject);
    }
}