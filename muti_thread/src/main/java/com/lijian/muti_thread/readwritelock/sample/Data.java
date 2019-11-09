package com.lijian.muti_thread.readwritelock.sample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Data {
    private final char[] buffer;
    //可重入 读写 锁
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true /* fair */);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }
//    读写锁 是 一体的 只有 写锁 写完了，才能 读，不然没写完 就读
    public char[] read() throws InterruptedException {
        readLock.lock();
        try {
            return doRead();
        } finally {
            readLock.unlock();
        }

    }
    public void write(char c) throws InterruptedException {
        writeLock.lock();
        try {
            doWrite(c);
        } finally {
            writeLock.unlock();
        }

    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }
    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }
}
