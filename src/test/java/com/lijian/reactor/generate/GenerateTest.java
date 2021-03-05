package com.lijian.reactor.generate;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateTest {
    // 自定义 数据流

//    generate是一种同步地，逐个地发出数据的方法。因为它提供的sink是一个SynchronousSink，
//    而且其next()方法在每次回调的时候最多只能被调用一次。







//    public static <T> Flux<T> generate(Consumer<SynchronousSink<T>> generator)
//
//    public static <T, S> Flux<T> generate(Callable<S> stateSupplier, BiFunction<S, SynchronousSink<T>, S> generator)
//
//    public static <T, S> Flux<T> generate(Callable<S> stateSupplier, BiFunction<S, SynchronousSink<T>, S> generator, Consumer<? super S> stateConsumer)

    @Test
    public void testGenerate1() {
        final AtomicInteger count = new AtomicInteger(1);   // 1 用于 计数
        Flux.generate(sink -> {
            sink.next(count.get() + " : " + new Date());   // 2 向 "池子" 放自定义的数据
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count.getAndIncrement() >= 5) {
                sink.complete();     // 3       告诉 generate 方法，自定义数据 已 发完
            }
        }).subscribe(System.out::println);  // 4    触发 数据流

//        Stream.generate(() -> "lijian").collect(Collectors.toList());
    }
// 增加 一个 伴随 状态
    @Test
    public void testGenerate2() {
        Flux.generate(
                () -> 1,    // 1 初始化 状态值
                (count, sink) -> {      // 2  参数 是 BiFunction，输入为状态和sink
                    sink.next(count + " : " + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 5) {
                        sink.complete();
                    }
                    return count + 1;   // 3 每次循环都要返回新的状态值给下次使用
                }).subscribe(System.out::println);
    }
// 支持 Consumer 参数 ， 支持 在 数据流 完成 后 执行
    @Test
    public void testGenerate3() {

        Flux.generate(
                () -> 1,
                (count, sink) -> {
                    sink.next(count + " : " + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 5) {
                        sink.complete();
                    }
                    return count + 1;
                }, System.out::println)     // 1 将 最后 的 count 打印 出来
//        如果 state 使用了数据库连接或者其他需要进行清理的资源，这个 Consumer lambda 可以用来在最后完成资源清理任务
                .subscribe(System.out::println);
    }

}
