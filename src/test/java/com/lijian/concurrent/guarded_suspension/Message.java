package com.lijian.concurrent.guarded_suspension;

import java.util.Objects;

public class Message {
    String id;
    String content;

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    //发送消息
    void send(Message msg) {

    }

    //MQ消息返回后调用 该方法
    void onMessage(Message msg) {

    }

    //处理web响应
    String handleWebReq() {
        Message msg1 = new Message("1", null);

        send(msg1);

        GuardedObject<Message> go = new GuardedObject<>();
        Message r = go.get(Objects::nonNull);
        return null;
    }
}
