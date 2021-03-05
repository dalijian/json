package com.lijian.reactor.self;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxArray<T> extends Flux<T> {
    private T[] array;  // 1

    public FluxArray(T[] data) {
        this.array = data;
    }

    //    subscribe方法通常会回调Subscriber的onSubscribe方法，该方法需要传入一个Subscription对象，
//    从而订阅者之后可以通过回调传回的Subscription的request方法跟FluxArray请求数据，这也是回压的应有之义。
    @Override
    public void subscribe(Subscriber<? super T> actual) {
        // 订阅者 调用 发布 对象
        actual.onSubscribe(new ArraySubscription<>(actual, array)); // 2
    }


    static class ArraySubscription<T> implements Subscription { // 1
        final Subscriber<? super T> actual; //对  订阅者 的 引用
        final T[] array;    // 2
        int index;
        boolean canceled;

        public ArraySubscription(Subscriber<? super T> actual, T[] array) {
            this.actual = actual;
            this.array = array;
        }
        // 订阅者 向 发布者 传达 订阅 个数
        @Override
        public void request(long n) {
            if (canceled) {
                return;
            }
            long length = array.length;
            for (int i = 0; i < n && index < length; i++) {
                                                 // index  指定 发布 顺序
                actual.onNext(array[index++]);  // 3 当 有 可以 发布 的 元素 时， 回调 订阅者 的 onNext 方法 传递 元素
            }
            if (index == length) {              // 当 index 等于 request() 请求 个数 时 发布 完成
                actual.onComplete();         // 4 当 所有 的 元素 都 发完时，回调 订阅者 的 onComplete方法
            }
        }

        @Override
        public void cancel() {  // 5    订阅者 可以 使用 Subscription 取消 订阅
            this.canceled = true;
        }
    }

}