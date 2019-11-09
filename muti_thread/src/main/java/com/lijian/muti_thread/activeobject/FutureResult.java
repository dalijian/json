package com.lijian.muti_thread.activeobject;

//封装返回结果 提货单
class FutureResult<T> extends Result<T> {
    private Result<T> result;
    private boolean ready = false;
    public synchronized void setResult(Result<T> result) {
        this.result = result;
        this.ready = true;
        notifyAll();
    }
    public synchronized T getResultValue() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return result.getResultValue();
    }
}
