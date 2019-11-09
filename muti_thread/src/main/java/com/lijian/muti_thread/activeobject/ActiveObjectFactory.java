package com.lijian.muti_thread.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
//        线程队列
        ActivationQueue queue = new ActivationQueue();
        //线程调度器
        SchedulerThread scheduler = new SchedulerThread(queue);


        Proxy proxy = new Proxy(scheduler, servant);
        scheduler.start();
        return proxy;
    }
}
