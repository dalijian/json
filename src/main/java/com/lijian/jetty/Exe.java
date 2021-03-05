package com.lijian.jetty;

import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Exe {

    private static final Logger logger = LoggerFactory.getLogger(Exe.class);

    public static void main(String[] args) throws IOException, JoranException {
        LogBackConfigLoader.load(Exe.class.getClassLoader().getResource("logback.xml").getPath());
        //代码
    }
}