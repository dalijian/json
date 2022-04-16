package com.a;

import org.apache.log4j.Logger;

public class AnObject {
    private static final Logger LOGGER = Logger.getLogger(AnObject.class);
    private final String name;
    
    public AnObject(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        System.out.println(LOGGER.toString()+" "+Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(LOGGER.toString()+" "+Thread.currentThread().getName());
        LOGGER.info("Logging DEBUG in AnObject [" + name + "] + AnObject");
        return name;
    }
}