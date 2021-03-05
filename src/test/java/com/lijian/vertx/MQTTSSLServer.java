package com.lijian.vertx;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import io.vertx.mqtt.MqttTopicSubscription;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MQTTSSLServer {
    public static void main(String[] args) {
//        MqttServerOptions options = new MqttServerOptions()
//                .setPort(8883)
//                .setKeyCertOptions(new PemKeyCertOptions()
//                        // 设置 私钥 路径 rsa_private_key.pem
//                        .setKeyPath("src/test/resources/rsa_private_key.pem")
//                        // 设置 证书 路径
//                        .setCertPath("./src/test/resources/tls/server-cert.pem"))
//                .setSsl(true);
        Vertx vertx =Vertx.vertx();
        MqttServer mqttServer = MqttServer.create(vertx);
        mqttServer.endpointHandler(endpoint -> {

            // 展示主要连接信息
            System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] request to connect, clean session = " + endpoint.isCleanSession());

            if (endpoint.auth() != null) {
                System.out.println("[username = " + endpoint.auth().userName() + ", password = " + endpoint.auth().password() + "]");
            }
            if (endpoint.will() != null) {
                System.out.println("[will topic = " + endpoint.will().willTopic() + " msg = " + endpoint.will().willMessage() +
                        " QoS = " + endpoint.will().willQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
            }

            System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");

            // 接受远程客户端连接
            endpoint.accept(false);

            // 处理 订阅
            endpoint.subscribeHandler(subscribe -> {

                List<MqttQoS> grantedQosLevels = new ArrayList<>();
                for (MqttTopicSubscription s: subscribe.topicSubscriptions()) {
                    System.out.println("Subscription for " + s.topicName() + " with QoS " + s.qualityOfService());
                    grantedQosLevels.add(s.qualityOfService());
                }
                // 确认订阅请求
                endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);

            });
            // 处理 取消
            endpoint.unsubscribeHandler(unsubscribe -> {

                for (String t: unsubscribe.topics()) {
                    System.out.println("Unsubscription for " + t);
                }
                // 确认订阅请求
                endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
            });

            // 处理 客户端 消息 发布
            endpoint.publishHandler(message -> {

                System.out.println("Just received message [" + message.payload().toString(Charset.defaultCharset()) + "] with QoS [" + message.qosLevel() + "]");

                if (message.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
                    endpoint.publishAcknowledge(message.messageId());
                } else if (message.qosLevel() == MqttQoS.EXACTLY_ONCE) {
                    endpoint.publishRelease(message.messageId());
                }

            }).publishReleaseHandler(messageId -> {

                endpoint.publishComplete(messageId);
            });
        })
                .listen(ar -> {

                    if (ar.succeeded()) {

                        System.out.println("MQTT server is listening on port " + ar.result().actualPort());
                    } else {

                        System.out.println("Error on starting the server");
                        ar.cause().printStackTrace();
                    }
                });
    }
}
