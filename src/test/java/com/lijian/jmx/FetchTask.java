package com.lijian.jmx;

public class FetchTask implements Runnable {
    private final String name;
 
    public FetchTask(String name) {
        this.name = name;
    }
 
    public String toString() {
        return "FetchTask: " + name;
    }
 
    public void run() {  /* Fetch remote resource */  }
}