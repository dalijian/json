package com.lijian.guava.eventbus;

import com.google.common.eventbus.Subscribe;

public class NumberListener {
       
    private Number lastMessage;  


    //监听事件类型 Number , Number 是 Integer,Long  等的 父类， 所以 Integer,Long  等 事件  更新 时， Number  也能 接受到
    @Subscribe
    public void listen(Number integer) {  
        lastMessage = integer; 
        System.out.println("Message:"+lastMessage);
    }  
   
    public Number getLastMessage() {  
        return lastMessage;  
    }  
}  

