package com.lijian.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestEventBus {
    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());

        TimeUnit.SECONDS.sleep(1L);

        eventBus.post(new TestEvent(500));

    }
}

//输出信息
//event message:200
//Message:200
//event message:300
//Message:300
//event message:400
//Message:400
//LastMessage:400