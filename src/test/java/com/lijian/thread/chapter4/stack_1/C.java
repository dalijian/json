package com.lijian.thread.chapter4.stack_1;

public class C {
    private MyStack myStack;

    public C(MyStack myStack) {
        super();
        this.myStack =myStack;

    }
    public void popService(){
        System.out.println("pop=" + myStack.pop());
    }

}
