package com.lijian.reactor.generate;

import com.lijian.reactor.MyEventListener;
import com.lijian.reactor.MyEventSource;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class    CreateTest {
//  create 是 一个 更 高级 的 创建 Flux 的方法，其 生成数据流 的方法
        // 既可以 是 同步的，
        // 也可以 是 异步的，并且还可以每次发出多个元素
    // FluxSink 支持 在 回调 中 触发 多个事件 (即使事件是发生在未来的某个时间)
    // create 常用的 场景 就是 将 现有 的 API转为响应式，比如监听器的异步方法




    //创建一个监听器注册到事件源，这个监听器再收到事件回调的时候通过Flux.create的sink将一系列事件转换成异步的事件流：
    @Test
    public void testCreate() throws InterruptedException {
        MyEventSource eventSource = new MyEventSource();    // 1 事件源
        Flux.create(sink -> {
                    eventSource.register(new MyEventListener() {    // 2 向事件源注册 用 匿名内部类 创建 的 监听器
                        @Override
                        public void onNewEvent(MyEventSource.MyEvent event) {
                            sink.next(event);       // 3    监听器 在 收到事件 回调 的时候 通过 sink 将 事件再 发出
                        }

                        @Override
                        public void onEventStopped() {
                            sink.complete();        // 4 监听器 再 收到事件源 停止 的 回调 的时候 通过 sink 发出 完成 信号
                        }
                    });
                }
        ).subscribe(System.out::println);       // 5    触发订阅

        for (int i = 0; i < 20; i++) {  // 6
            Random random = new Random();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            eventSource.newEvent(new MyEventSource.MyEvent(new Date(), "Event-" + i));
        }
        eventSource.eventStopped(); // 7  停止 事件源
    }


//    @Test
//    public void fluxPut(){
//
//        Flux<String> bridge = Flux.create(sink -> {
//                    myMessageProcessor.register(
//                            new MyMessageListener<String>() {
//
//                                public void onMessage(List<String> messages) {
//                                    for(String s : messages) {
//                                        sink.next(s);   // 1 push 方法，主动向 下游 发出 数据
//                                    }
//                                }
//                            });
//                    sink.onRequest(n -> {   // 2    在 下游 发出 请求 时 被 调用
//                        List<String> messages = myMessageProcessor.request(n);  // 3 响应 下游 的请求，查询是否 有 可用 的 message
//                        for(String s : message) {
//                            sink.next(s);
//                        }
//                    });
//
//                }
//    }
}
