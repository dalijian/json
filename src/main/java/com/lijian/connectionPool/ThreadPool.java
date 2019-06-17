package com.lijian.connectionPool;

public interface ThreadPool <Job extends Runnable > {

    void execute(Job job);

}
