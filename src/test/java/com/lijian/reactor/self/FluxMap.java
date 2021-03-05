package com.lijian.reactor.self;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;

public class FluxMap<T, R> extends Flux<R> {

    private final Flux<? extends T> source; // 上游 Flux
    private final Function<? super T, ? extends R> mapper; // map 支持 的 function

    public FluxMap(Flux<? extends T> source, Function<? super T, ? extends R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public void subscribe(Subscriber<? super R> actual) {
        // 向 上游 发布者 订阅 时 将 订阅者 封装 在 MapSubscriber 中
        source.subscribe(new MapSubscriber<>(actual, mapper));
    }

//    @Override
//    public void subscribe(Subscriber<? super R> s) {
//
//    }

//    static final class MapSubscriber<T, R> implements Subscription {
//        private final Subscriber<? super R> actual;
//        private final Function<? super T, ? extends R> mapper;
//
//          MapSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
//            this.actual = actual;
//            this.mapper = mapper;
//        }
//
//        @Override
//        public void request(long n) {   // 1 但是 map 操作符 并 不产生 数据，只是 数据 的 搬运工 ，收到 request 后 要 发出 的 数据 来自 上游
//            // TODO 收到请求，发出元素
//
//        }
//
//        @Override
//        public void cancel() {
//            // TODO 取消订阅
//        }
//    }

    static final class MapSubscriber<T, R> implements Subscriber<T>, Subscription {
        private final Subscriber<? super R> actual;
        private final Function<? super T, ? extends R> mapper;

        boolean done;

        Subscription subscriptionOfUpstream;

        MapSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }
        //上游 FLux 接受 subscribe 通知 后 调用 onSubscribe
        @Override
        public void onSubscribe(Subscription s) {
            this.subscriptionOfUpstream = s;    // 1 拿到 来自 上游 的 Subscription

            actual.onSubscribe(this);        // 2 回调下游的 onSubscribe，将自身作为 Subscription 传递 过去
        }

        @Override
        public void onNext(T t) {
            if (done) {
                return;
            }
            actual.onNext(mapper.apply(t));     // 3 收到上游发出的数据 后，将其用mapper进行转换，然后接着发给下游
        }

        @Override
        public void onError(Throwable t) {
            if (done) {
                return;
            }
            done = true;
            actual.onError(t);                  // 4 将上游的错误信后 原样发给 下游
        }

        @Override
        public void onComplete() {
            if (done) {
                return;
            }
            done = true;
            actual.onComplete();                // 5 将 上游 的 完成 信号 原样 发给 下游
        }

        @Override
        public void request(long n) {
            this.subscriptionOfUpstream.request(n);     // 6 将 下游 的请求 传递 给 上游
        }

        @Override
        public void cancel() {
            this.subscriptionOfUpstream.cancel();       // 7 将 下游 的取消操作传递给 上游
        }
    }
}