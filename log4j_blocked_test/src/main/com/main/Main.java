package com.main;

import com.c.AnObjectThread;
import com.d.AnExceptionThread;
import com.e.AnObjectThreadC;

/**
 * Bug 41214 reproduction.
 *
 * https://issues.apache.org/bugzilla/show_bug.cgi?id=41214
 *
 * @author Marcelo S. Miashiro
 */
public class Main {
    public static void main(String[] args) {
        RootLoggerThread rootLoggerThread = new RootLoggerThread("RootLoggerThread");
        AnObjectThread anObjectThread = new AnObjectThread("AnObjectThread");
//        AnObjectThreadC anObjectThreadc = new AnObjectThreadC("AnObjectThreadC");

//        AnExceptionThread anExceptionThread = new AnExceptionThread("AnExceptionThread");
//        anExceptionThread.start();
        anObjectThread.start();

        try {
            // To reproduce the bug, com.a.AnObject.toString() and com.b.AnException.getMessage()
            // methods must be called before rootLogger
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        rootLoggerThread.start();
//        anObjectThreadc.start();
    }
}