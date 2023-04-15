package com.lijian.concurrent.chapter5;

import java.util.List;

//等待 通知 机制 wait notify
public class Allocator {
    private List<Object> als;

    synchronized void apply(Object from, Object to) {

        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
