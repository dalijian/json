package com.lijian.muti_thread.balking.sample.my;

public class Main {
    public static void main(String[] args) {
Data data = new Data();
        data.setFileName("balking.txt");
        ChangeThread changeThread = new ChangeThread(data, "changerTHread");
        SaverThread saverThread = new SaverThread(data, "saverThread");
        Thread thread = new Thread(changeThread, changeThread.getThreadName());
        Thread thread1 = new Thread(saverThread, saverThread.getThreadName());

        thread.start();
        thread1.start();

    }
}
