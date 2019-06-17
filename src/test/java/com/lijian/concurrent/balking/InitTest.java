package com.lijian.concurrent.balking;
//多线程初始化执行
public class InitTest {
    boolean inited =false;
    synchronized void init(){
        if (inited) {
            return;
        }
        doInit();
        inited =true;
    }

    private void doInit() {
    }
}
