package com.lijian.reactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test_2_3 {


    private final int EVENT_DURATION = 10;    // 生成的事件间隔时间，单位毫秒
    private final int EVENT_COUNT = 20;    // 生成的事件个数
    private final int PROCESS_DURATION = 30; // 订阅者处理每个元素的时间，单位毫秒


    private Flux<MyEventSource.MyEvent> fastPublisher;

    private SlowSubscriber slowSubscriber;

    private MyEventSource eventSource;

    private CountDownLatch countDownLatch;

    /**
     * 准备工作。
     */
    @Before
    public void setup() {
        countDownLatch = new CountDownLatch(1);
        slowSubscriber = new SlowSubscriber();
        eventSource = new MyEventSource();
    }

    /**
     * 触发订阅，使用CountDownLatch等待订阅者处理完成。
     */
    @After
    public void subscribe() throws InterruptedException {
        // 订阅
        fastPublisher.subscribe(slowSubscriber);

        // 生成 事件
        generateEvent(EVENT_COUNT, EVENT_DURATION);

        countDownLatch.await(1, TimeUnit.MINUTES);
    }

    /**
     * 内部类，“慢的订阅者”。
     */
    class SlowSubscriber extends BaseSubscriber<MyEventSource.MyEvent> {


        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            request(1);     // 订阅时请求1个数据
        }

        @Override
        protected void hookOnNext(MyEventSource.MyEvent event) {
            System.out.println("                      receive <<< " + event.getMessage());
            try {
                TimeUnit.MILLISECONDS.sleep(PROCESS_DURATION);
            } catch (InterruptedException e) {
            }
            request(1);     // 每处理完1个数据，就再请求1个
        }

        @Override
        protected void hookOnError(Throwable throwable) {
            System.err.println("                      receive <<< " + throwable);
        }

        @Override
        protected void hookOnComplete() {
            countDownLatch.countDown();
        }
    }


    /**
     * 使用create方法生成“快的发布者”。
     *
     * @param strategy 回压策略
     * @return Flux
     */
    private Flux<MyEventSource.MyEvent> createFlux(FluxSink.OverflowStrategy strategy) {
        return Flux.create(sink -> eventSource.register(new MyEventListener() {
            @Override
            public void onNewEvent(MyEventSource.MyEvent event) {
                System.out.println("publish >>> " + event.getMessage());
                sink.next(event);
            }

            @Override
            public void onEventStopped() {
                sink.complete();
            }
        }), strategy);  // 1
    }

    /**
     * 生成MyEvent。
     *
     * @param count  生成MyEvent的个数。
     * @param millis 每个MyEvent之间的时间间隔。
     */
    private void generateEvent(int count, int millis) {
        // 循环生成MyEvent，每个MyEvent间隔millis毫秒
        for (int i = 0; i < count; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(millis);
            } catch (InterruptedException e) {
            }
            eventSource.newEvent(new MyEventSource.MyEvent(new Date(), "Event-" + i));
        }
        eventSource.eventStopped();
    }


    /**
     * 测试create方法的不同OverflowStrategy的效果。
     */
    @Test
    public void testCreateBackPressureStratety() {
        // 使用 默认 缓存
        fastPublisher =
                createFlux(FluxSink.OverflowStrategy.BUFFER)    // 1
                        .doOnRequest(n -> System.out.println("         ===  request: " + n + " ==="))    // 2 打印 出 每次 的 请求
                        .publishOn(Schedulers.newSingle("newSingle"), 1);   // 3 使用 publishOn 让 后续 的操作符和订阅者 运行 在 一个 单独的名为 newSingle的线程上
        // 第 二个参数1 是预取个数，也就是.publishOn 作为订阅者每次向上游request的个数，默认为256，所以一定程度上也起到了缓存的效果，为了测试，设置为1.
    }


    /**
     * 测试不同的onBackpressureXxx方法的效果。
     */
    @Test
    public void testOnBackPressureXxx() {
        fastPublisher = createFlux(FluxSink.OverflowStrategy.BUFFER)
                .onBackpressureBuffer()     // BUFFER
//                .onBackpressureDrop()     // DROP
//                .onBackpressureLatest()   // LATEST
//                .onBackpressureError()    // ERROR
                .doOnRequest(n -> System.out.println("         ===  request: " + n + " ==="))
                .publishOn(Schedulers.newSingle("newSingle"), 1);
    }

}