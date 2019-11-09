package com.lijian.muti_thread.balking.sample.my;



import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@lombok.Data
public class Data {
    private String message; // changeThread  改变 这个值，saverThread 保存这个值
//    private volatile boolean change;  // 将 change 修饰 成 volatile 没用
    private boolean change;
    private String fileName;

    public  synchronized void save() throws IOException {

//        java 使用 mesa  管程模型
//        MESA管程里面，T2通知完T1后，T2还是会接着执行，T1并不立即执行，仅仅是从条件变量的等待队列进到入口等待队列里面。这样做的好处是notify（）不用放到代码的最后，T2也没有多余的阻塞唤醒操作，但是也有个副作用，就是当T1再次执行的时候，可能曾经满足的条件，现在已经不满足了，所以需要以循环方式检验条件变量。
//        所以必须使用while 不能 使用 if
            while (!change) {
                System.out.println("balking return: "+message);
                return;
            }
        System.out.println("message_" + message);
        Writer fileWriter = new FileWriter(fileName, true);
            fileWriter.write(message);
            this.change =false;
            fileWriter.close();

    }
    public void change(String message){
        this.message = message;
        this.change = true;
    }

}
