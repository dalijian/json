package com.lijian.muti_thread.activeobject.sample;


import com.lijian.muti_thread.activeobject.ActiveObject;
import com.lijian.muti_thread.activeobject.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }
}
