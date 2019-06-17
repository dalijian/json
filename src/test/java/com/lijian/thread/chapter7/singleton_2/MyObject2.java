package com.lijian.thread.chapter7.singleton_2;

import java.io.Serializable;

public class MyObject2  implements Serializable {

    private static MyObject2 myObject;

    private MyObject2() {

    }


private static class MyObjectHandler{
        private static MyObject2 myObject = new MyObject2();

}
public static MyObject2 getInstance4(){
        return MyObjectHandler.myObject;
}

protected Object readResolve(){
    System.out.println("调用了 readResolve");
    return MyObjectHandler.myObject;
}
}
