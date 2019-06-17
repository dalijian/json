package com.lijian.thread.chapter7.singleton_2;

public class MyObject {
    private static MyObject myObject;

    private MyObject() {

    }

    public static MyObject getInstance() {

        synchronized (MyObject.class) {


            try {
                if (myObject != null) {

                } else {
                    Thread.sleep(3000);
                    myObject = new MyObject();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return myObject;
        }
    }
    public static MyObject getInstance2() {



            try {
                if (myObject != null) {

                } else {
                    Thread.sleep(3000);
                    synchronized (MyObject.class) {


                        myObject = new MyObject();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return myObject;
        }
    public static MyObject getInstance3() {



            try {
                if (myObject != null) {

                } else {
                    Thread.sleep(3000);
                    synchronized (MyObject.class) { //双检查
                        if (myObject == null) {
                            myObject = new MyObject();
                        }
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return myObject;
        }

private static class MyObjectHandler{
        private static MyObject myObject = new MyObject();

}
public static MyObject getInstance4(){
        return MyObjectHandler.myObject;
}

}
