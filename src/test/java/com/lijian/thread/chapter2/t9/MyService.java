package com.lijian.thread.chapter2.t9;

import com.lijian.thread.chapter2.synchronizedMethodLockObject.MyObject;

public class MyService {

    public MyOneList addServiceMethod(MyOneList list, String data) {
        synchronized (list) { // 由于list 参数对象 在项目中是一份实例，是单例的，而且 也正需要对list参数的getSize（）方法做同步的调用，所以就对list参数进行同步处理
        if (list.getSize() < 1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(data);

        }
        return list;
    }
    }
}
