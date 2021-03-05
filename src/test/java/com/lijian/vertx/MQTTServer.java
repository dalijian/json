package com.lijian.vertx;


import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttTopicSubscription;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import io.vertx.rxjava.mqtt.MqttServer;
//import io.vertx.mqtt.MqttServer;

public class MQTTServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        MqttServer mqttServer = MqttServer.create(vertx);
        mqttServer.endpointHandler(endpoint -> {

            // 显示主要连接信息
            System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] request to connect, clean session = " + endpoint.isCleanSession());

            if (endpoint.auth() != null) {
                System.out.println("[username = " + endpoint.auth().getUsername() + ", password = " + endpoint.auth().getPassword() + "]");
            }
            if (endpoint.will().getWillTopic() != null) {
                System.out.println("[will topic = " + endpoint.will().willTopic() + " msg = " + endpoint.will().willMessage() +
                        " QoS = " + endpoint.will().willQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
            }
            if (endpoint.isAutoKeepAlive()) {
                System.out.println("client is auto keepAlive");

            }else{
                endpoint.pong();
            }

            System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");


            // 接受远程客户端连接
            endpoint.accept(false);





            // 处理 客户端 断开连接
            endpoint.disconnectHandler(v -> {

                System.out.println("Received disconnect from client");
            });
            // 处理 订阅
            endpoint.subscribeHandler(subscribe -> {
                List<MqttQoS> grantedQosLevels = new ArrayList<>();
                for (MqttTopicSubscription s : subscribe.topicSubscriptions()) {
                    System.out.println("Subscription for " + s.topicName() + " with QoS " + s.qualityOfService());
                    grantedQosLevels.add(s.qualityOfService());
                }
                // 确认订阅请求
                endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);

            });





            // 处理 取消
            endpoint.unsubscribeHandler(unsubscribe -> {

                for (String t : unsubscribe.topics()) {
                    System.out.println("Unsubscription for " + t);
                }
                // 确认订阅请求
                endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
            });





            // 处理 客户端 消息 发布
            endpoint.publishHandler(message -> {

                System.out.println("Just received message [" + message.payload().toString(Charset.defaultCharset()) + "] with QoS [" + message.qosLevel() + "]");
                if (message.qosLevel() == MqttQoS.AT_MOST_ONCE) {
                    System.out.println("不做处理");
                }
                // puback Server向Client发布该确认（Client收到确认后删除），订阅者向Server发布确认。
                if (message.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
                    endpoint.publishAcknowledge(message.messageId());

//                    PUBREC / PUBREL / PUBCOMP
//                    QoS=2时
//                    1. Server->Client发布PUBREC（已收到）；
//                    2. Client->Server发布PUBREL（已释放）；
//                    3. Server->Client发布PUBCOMP（已完成），Client删除msg；
//                    订阅者也会向Server发布类似过程确认。

                } else if (message.qosLevel() == MqttQoS.EXACTLY_ONCE) {
//                    endpoint.publishRelease(message.messageId());
                    System.out.println(message.messageId());
                    endpoint.publishReceived(message.messageId());
                }

            }).publishReleaseHandler(messageId -> {

                endpoint.publishComplete(messageId);
            });


            System.out.println("发布 my_topic 消息 ");
            // 例子, 发布一个QoS级别为2的消息
            // 为什么 只能在 重启的 时候 发布
            endpoint.publish("my_topic", Buffer.buffer("Hello from the Vert.x MQTT server"), MqttQoS.AT_MOST_ONCE, false, false);


            endpoint.publishAcknowledgeHandler(messageId ->{
                System.out.println("received ack for message =" + messageId);

            } );
            endpoint.publishReceivedHandler(messageId->{
                endpoint.publishRelease(messageId);
            });
            endpoint.publishCompletionHandler(messageId->  {
                System.out.println("received ack for message =" + messageId);
            });


            // 选定handlers处理QoS 1与QoS 2
//            endpoint.publishAcknowledgeHandler((messageId: java.lang.Integer) => {
//
//                println(s"Received ack for message = ${messageId}")
//
//            }).publishReceivedHandler((messageId: java.lang.Integer) => {
//
//                endpoint.publishRelease(messageId)
//
//            }).publishCompleteHandler((messageId: java.lang.Integer) => {
//
//                println(s"Received ack for message = ${messageId}")
//            })


















        }).listen(ar -> {

            if (ar.succeeded()) {

                System.out.println("MQTT server is listening on port " + ar.result().actualPort());
            } else {

                System.out.println("Error on starting the server");
                ar.cause().printStackTrace();
            }
        });

//        mqttServer.close(event -> {
//            if (event.succeeded()) {
//                System.out.println("success");
//
//            }
//            if (event.failed()) {
//                System.out.println("failure");
//            }
//        });

    }
}
