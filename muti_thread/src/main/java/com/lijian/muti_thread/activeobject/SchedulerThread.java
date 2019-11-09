package com.lijian.muti_thread.activeobject;
//与client 角色 运行 雨不同 的线程，
class SchedulerThread extends Thread {
    private final ActivationQueue queue;
    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }
    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }
    public void run() {
        while (true) {
            MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }
}
