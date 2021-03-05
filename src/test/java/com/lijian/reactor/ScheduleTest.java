package com.lijian.reactor;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ScheduleTest {
    @Before
            public void openDebug(){
        Hooks.onOperatorDebug();
    }


    @Test
    public void testScheduling() {
        Flux.range(0, 10)
//                .log()
                .publishOn(Schedulers.newParallel("myParallel"))
//                .log()
                .subscribeOn(Schedulers.newElastic("myElastic"))
                .log()
                .blockLast();
    }


    @Test
    public void testParallelFlux() throws InterruptedException {
        Flux.range(1, 10)
                .publishOn(Schedulers.parallel())
                .log().subscribe();
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Test
    public void testParallelFlux2() throws InterruptedException {
        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.parallel())
//                .publishOn(Schedulers.parallel())
                .log()
                .subscribe();

        TimeUnit.MILLISECONDS.sleep(10);
    }




//    transform可以将一段操作链打包为一个函数式。这个函数式能在组装期将被封装的操作符还原并接入到调用transform的位置

//    就是 封装 代码块 ，复用
    @Test
    public void testTransform() {
        Function<Flux<String>, Flux<String>> filterAndMap =
                f -> f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);

        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .transform(filterAndMap)
                .subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: "+d));
    }

//    compose 这个函数式是针对每一个订阅者起作用的。这意味着它对每一个 subscription 可以生成不同的操作链
@Test
    public void testCompose() {
        AtomicInteger ai = new AtomicInteger();
        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };

        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .transformDeferred(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
    }


}
