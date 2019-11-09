package com.lijian.muti_thread.activeobject;

//使用多态将 请求 封装
abstract class MethodRequest<T> {
//    请求执行 者
    protected final Servant servant;
//    请求执行结果
    protected final FutureResult<T> future;
    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }
//    请求执行方法
    public abstract void execute();
}
