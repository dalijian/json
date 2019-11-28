package com.lijian.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

public class TestEventsFromSubclass {
    @Test
    public void testEventsFromSubclass() throws Exception {  
       
        EventBus eventBus = new EventBus("test");
        IntegerListener integerListener = new IntegerListener();  
        NumberListener numberListener = new NumberListener();  
        eventBus.register(integerListener);  
        eventBus.register(numberListener);  
       
        eventBus.post(new Integer(100));  
       
        System.out.println("integerListener message:"+integerListener.getLastMessage());
        System.out.println("numberListener message:"+numberListener.getLastMessage());
              
        eventBus.post(new Long(200L));  
       
        System.out.println("integerListener message:"+integerListener.getLastMessage());
        System.out.println("numberListener message:"+numberListener.getLastMessage());        
    }  
}

//输出类
//Message:100
//Message:100
//integerListener message:100
//numberListener message:100
//Message:200
//integerListener message:100
//numberListener message:200