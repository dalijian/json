package com.lijian.reactor.self;

import com.lijian.reactor.self.Flux;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxTest {

    @Test
    public void fluxArrayTest() {
        // 订阅者 通过 内部类 实现 ， 当 执行 subscribe 方法 时，发布者 会 回调 订阅者的 onSubscribe方法
        Flux.just(1, 2, 3, 4, 5).subscribe(new Subscriber<Integer>() { // 1
            // 订阅者 向  借助 Subscription 向 发布者 请求 n 个 数据。
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(6);   // 2  订 阅 时 请 求 6 个 元素
                //    Subscription 相当于 publish 和 Subscriber 之间 的 中间 人
            }

            // 发布者 通过 不断 调用 订阅者 的 onNext 方法 向 订阅者 发出 最多 n 个 数据
            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer);
            }

            // 如果 有 错误 发生 ，则 通过 onError 发出 错误 数据 ，同样 也会 终止流
            @Override
            public void onError(Throwable t) {

            }

            //如果数据全部发完，则会调用 onComplete告知 订阅者 流 已经 发完
            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

        });
    }

    @Test
    public  void mapTest(){

      Flux.just(1, 2, 3, 4, 5)
                .map(i -> i * i).subscribe(new Subscriber<Integer>() { // 1
                    // 订阅者 向  借助 Subscription 向 发布者 请求 n 个 数据。
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe");
                        s.request(6);   // 2  订 阅 时 请 求 6 个 元素
                        //    Subscription 相当于 publish 和 Subscriber 之间 的 中间 人
                    }

                    // 发布者 通过 不断 调用 订阅者 的 onNext 方法 向 订阅者 发出 最多 n 个 数据
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext:" + integer);
                    }

                    // 如果 有 错误 发生 ，则 通过 onError 发出 错误 数据 ，同样 也会 终止流
                    @Override
                    public void onError(Throwable t) {

                    }

                    //如果数据全部发完，则会调用 onComplete告知 订阅者 流 已经 发完
                    @Override
                    public void onComplete() {
                        System.out.println("onComplete") ;}
                });

    }

}
