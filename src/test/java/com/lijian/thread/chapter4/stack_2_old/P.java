package com.lijian.thread.chapter4.stack_2_old;

public class P {
    private MyStack myStack;

    public P(MyStack myStack) {
        super();
        this.myStack = myStack;

    }
    public void pushService(){
        myStack.push();
    }

}
