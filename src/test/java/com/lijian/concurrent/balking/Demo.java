package com.lijian.concurrent.balking;

public class Demo {
    boolean changed =false;

    //自动存盘操作
    void autoSave(){
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed=false;
        }

        //执行存盘操作
        //省略且实现
        this.execSave();

    }

    private void execSave() {
    }

    void edit(){


//        ......
        change();

    }
    void change(){
        synchronized (this) {
            changed = true;
        }
    }
}
