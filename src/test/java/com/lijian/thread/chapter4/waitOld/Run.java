package com.lijian.thread.chapter4.waitOld;
public class Run {
    public static void main(String[] args) {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubject threadSubject = new ThreadSubject(subtract);
        threadSubject.setName("subtract1Thread");
        threadSubject.start();
        ThreadAdd threadSubject1= new ThreadAdd(add);
        threadSubject1.setName("subtract2Thread");
        threadSubject1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadAdd app= new ThreadAdd(add);
        app.setName("addThread");
        app.start();
    }
}
