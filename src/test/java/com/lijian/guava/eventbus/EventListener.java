package com.lijian.guava.eventbus;

import com.google.common.eventbus.Subscribe;

//  现在 发布者 - 订阅者 模式，订阅者 不需要 持有 发布者 对象了，
// 原先 订阅者 持有 发布者 对象，当 发布者 更新时，发布者 通过 注册 的 注册 订阅者，调用自身 接口，从而 实现 消息更新，主动 推向 订阅者
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(String  event) {
//        lastMessage = event.getMessage();
//        System.out.println("Message:"+lastMessage);
        System.out.println(event);
    }

    public int getLastMessage() {      
        return lastMessage;
    }
}