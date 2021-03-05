package com.lijian.reactor;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import reactor.util.annotation.Nullable;
import reactor.util.context.Context;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReactorTest {

    Logger logger = LoggerFactory.getLogger(ReactorTest.class);
    @Test
    public void fluxTest() {

//        Flux 表示的是包含 0 到 N 个元素的异步序列
//       在该序列中可以包含三种不同类型的消息通知：正常的包含元素的消息、序列结束的消息和序列出错的消息
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(1000)).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(1000)).subscribe(System.out::println);
    }

    @Test
    public void emptyTest() {
        // 只有完成信号的空数据流
        Flux.just();
        Flux.empty();
        Mono.empty();
        Mono.justOrEmpty(Optional.empty());
// 只有错误信号的数据流
        Flux.error(new Exception("some error"));
        Mono.error(new Exception("some error"));
    }

    //打印出了错误信号，没有输出Completed!表明没有发出完成信号
    @Test
    public void subsribeTest() {
        Mono.error(new Exception("some error")).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Completed!")
        );

    }


    private Flux<Integer> generateFluxFrom1To6() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }

    private Mono<Integer> generateMonoWithError() {
        return Mono.error(new Exception("some error"));
    }

    //    expectNext用于测试下一个期望的数据元素，
// expectErrorMessage用于校验下一个元素是否为错误信号，
// expectComplete用于测试下一个元素是否为完成信号。
    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(generateFluxFrom1To6())
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        StepVerifier.create(generateMonoWithError())
                .expectErrorMessage("some error")
                .verify();
    }


    //    Mono 表示的是包含 0 或者 1 个元素的异步序列。
//    该序列中同样可以包含与 Flux 相同的三种类型的消息通知
    @Test
    public void monoTest() {

        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

    @Test
    public void bufferTest() {
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//        Flux.interval(Duration.ofMillis(100)).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    public void filterTest() {
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    public void windowTest() {
//        所不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>
        Flux.range(1, 100).window(20).subscribe(System.out::println);
//        Flux.interval(Duration.ofMillis(1000)).windowMillis(1001).take(2).toStream().forEach(System.out::println);
    }
//    zip - 一对一合并
    @Test
    public void zipWithTest() {
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }

    private Flux<String> getZipDescFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));  // 1
    }




    @Test
    public void testSimpleOperators() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);  // 2
        Flux.zip(
                getZipDescFlux(),
                Flux.interval(Duration.ofMillis(200)))  // 3
                .subscribe(t -> System.out.println(t.getT1()), null, countDownLatch::countDown);    // 4
        countDownLatch.await(10, TimeUnit.SECONDS);     // 5
    }

//    用于编程方式自定义生成数据流的create和generate等及其变体方法；
//    用于“无副作用的peek”场景的doOnNext、doOnError、doOncomplete、doOnSubscribe、doOnCancel等及其变体方法；
//    用于数据流转换的when、and/or、merge、concat、collect、count、repeat等及其变体方法；
//    用于过滤/拣选的take、first、last、sample、skip、limitRequest等及其变体方法；
//    用于错误处理的timeout、onErrorReturn、onErrorResume、doFinally、retryWhen等及其变体方法；
//    用于分批的window、buffer、group等及其变体方法；
//    用于线程调度的publishOn和subscribeOn方法。
    @Test
    public void takeTest() {
        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
    }

    @Test
    public void reduceTest() {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

    }
    @Test
    public void intervalTest(){
        // Flux.interval 从 0 开始 递增
        List<Long> result = Flux.interval(Duration.ofMillis(0), Duration.ofMillis(10)).take(10).toStream().collect(Collectors.toList());
        System.out.println(result);
    }
    @Test
    public void mergeTest() {
        Flux.merge(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
        Flux.mergeSequential(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
    }


    //    flatMap - 元素映射为流
    @Test
    public void flatMapTest2() {
        StepVerifier.create(
                Flux.just("flux", "mono")
                        .flatMap(s -> Flux.fromArray(s.split("\\s*"))   // 1
                                .delayElements(Duration.ofMillis(1000))) // 2
                        .doOnNext(System.out::print)) // 3
                .expectNextCount(8) // 4
                .verifyComplete();
    }

    @Test
    public void flatMapTest() {
        Flux.just(5, 10)
                .flatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    public void concatMapTest() {
        Flux.just(5, 10)
                .concatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    public void combineNextLatest() {
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.ofMillis(1000)).take(5),
                Flux.interval(Duration.ofMillis(1000), Duration.ofMillis(50)).take(5)
        ).toStream().forEach(System.out::println);
    }

//    @Test
//    public void subscribeTest(){
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .subscribe(System.out::println, System.err::println);
//
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .onErrorReturn(0)
//                .subscribe(System.out::println);
//
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .switchOnError(Mono.just(0))
//                .subscribe(System.out::println);
//
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalArgumentException()))
//                .onErrorResumeWith(e -> {
//                    if (e instanceof IllegalStateException) {
//                        return Mono.just(0);
//                    } else if (e instanceof IllegalArgumentException) {
//                        return Mono.just(-1);
//                    }
//                    return Mono.empty();
//                })
//                .subscribe(System.out::println);
//
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .retry(1)
//                .subscribe(System.out::println);
//    }




//    当前线程（Schedulers.immediate()）；
//    可重用的单线程（Schedulers.single()）。注意，这个方法对所有调用者都提供同一个线程来使用， 直到该调度器被废弃。如果你想使用独占的线程，请使用Schedulers.newSingle()；
//    弹性线程池（Schedulers.elastic()）。它根据需要创建一个线程池，重用空闲线程。线程池如果空闲时间过长 （默认为 60s）就会被废弃。对于 I/O 阻塞的场景比较适用。Schedulers.elastic()能够方便地给一个阻塞 的任务分配它自己的线程，从而不会妨碍其他任务和资源；
//    固定大小线程池（Schedulers.parallel()），所创建线程池的大小与CPU个数等同；
//    自定义线程池（Schedulers.fromExecutorService(ExecutorService)）基于自定义的ExecutorService创建 Scheduler（虽然不太建议，不过你也可以使用Executor来创建）。

    private String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }
//    Schedulers.elastic()能够方便地给一个阻塞的任务分配专门的线程，从而不会妨碍其他任务和资源

//    我们就可以利用这一点将一个同步阻塞的调用调度到一个自己的线程中，并利用订阅机制，待调用结束后异步返回


//    public final Disposable subscribe(
//            @Nullable Consumer<? super T> consumer,
//            @Nullable Consumer<? super Throwable> errorConsumer,
//            @Nullable Runnable completeConsumer) {
//        return subscribe(consumer, errorConsumer, completeConsumer, (Context) null);
//    }




//    fromCallable声明一个基于Callable的Mono

//    subscribeOn将任务调度到Schedulers内置的弹性线程池执行，弹性线程池会为Callable的执行任务分配一个单独的线程

//    publishOn和subscribeOn。它们都接受一个 Scheduler作为参数，从而可以改变调度器。

//    但是publishOn在链中出现的位置是有讲究的，而subscribeOn 则无所谓。
    @Test
    public void testSyncToAsync() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> getStringSync())    // 1
                .subscribeOn(Schedulers.elastic())  // 2
                .subscribe(System.out::println, null, countDownLatch::countDown);

        countDownLatch.await(10, TimeUnit.SECONDS);
    }


    @Test
    public void schedulerTest() {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }

//    在响应式流中，错误（error）是终止信号。
//    当有错误发生时，它会导致流序列停止，并且错误信号会沿着操作链条向下传递，直至遇到subscribe中的错误处理方法。
//    这样的错误还是应该在应用层面解决的。否则，你可能会将错误信息显示在用户界面，或者通过某个REST endpoint发出。
//    所以还是建议在subscribe时通过错误处理方法妥善解决错误。

//    @Test
//    public void testErrorHandling() {
//        Flux.range(1, 6)
//                .map(i -> 10/(i-3)) // 1
//                .map(i -> i*i)
//                .subscribe(System.out::println, System.err::println,System.out::println);
//
//
//        // 异常 处理  抛出 缺省值
//        //
//        Flux.range(1, 6)
//                .map(i -> 10/(i-3))
//                .onErrorReturn(0)   // 1
//                .map(i -> i*i)
//                .subscribe(System.out::println, System.err::println);
//
//        // 异常 处理 提供新的数据流
//        Flux.range(1, 6)
//                .map(i -> 10/(i-3))
//                .onErrorResume(e -> Mono.just(new Random().nextInt(6))) // 提供新的数据流
//                .map(i -> i*i)
//                .subscribe(System.out::println, System.err::println);
//
//        // 捕捉 异常 封装 异常 抛出
//        Flux.just("timeout1")
//                .flatMap(k -> callExternalService(k))   // 1
//                .onErrorMap(original -> new BusinessException("SLA exceeded", original)); // 2
//
//        // 捕捉 记录错误 日志，然后继续抛出
////        形如doOnXxx是只读的，对数据流不会造成影响
//        Flux.just("endpoint1", "endpoint2")
//                .flatMap(k -> callExternalService(k))
//                .doOnError(e -> {   // 1
//                    logger.error("uh oh, falling back, service failed for key " + e);    // 2
//                })
//                .onErrorResume(e -> getFromCache(e));
//
//        //使用 finally 来清理资源，或使用 Java 7 引入的 “try-with-resource”
//        Flux.using(
//                () -> getResource(),    // 1
//                resource -> Flux.just(resource.getAll()),   // 2
//                MyResource::clean   // 3
//        );
//
//
//        LongAdder statsCancel = new LongAdder();    // 1
//
//        Flux<String> flux =
//                Flux.just("foo", "bar")
//                        .doFinally(type -> {
//                            if (type == SignalType.CANCEL)  // 2
//                                statsCancel.increment();  // 3
//                        })
//                        .take(1);   // 4
//
//
//// 重试
//        Flux.range(1, 6)
//                .map(i -> 10 / (3 - i))
//                .retry(1)
//                .subscribe(System.out::println, System.err::println);
//        try {
//            Thread.sleep(100);  // 确保序列执行完
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

// 背压 backPressure



//   1. Flux.range是一个快的Publisher；
//   2. 在每次request的时候打印request个数；
//   3. 通过重写BaseSubscriber的方法来自定义Subscriber；
//   4. hookOnSubscribe定义在订阅的时候执行的操作；
//   5. 订阅时首先向上游请求1个元素；
//   6. hookOnNext定义每次在收到一个元素的时候的操作；
//   7. sleep 1秒钟来模拟慢的Subscriber；
//   8. 打印收到的元素；
//   9. 每次处理完1个元素后再请求1个。

    @Test
    public void testBackpressure() {
        Flux.range(1, 6)    // 1
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))    // 2
                .subscribe(new BaseSubscriber<Integer>() {  // 3
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) { // 4
                        System.out.println("Subscribed and make a request...");
                        request(1); // 5
                    }

                    @Override
                    protected void hookOnNext(Integer value) {  // 6
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 7
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");    // 8
                        request(1); // 9
                    }
                });
    }

    private <D> D getResource(D d) {
        return d;
    }

    public Object getFromCache(Throwable e) {
        return null;
    }

    private Publisher<?> callExternalService(String k) {
        return null;
    }

    @Test
    public void stepVerifierTest() {
        StepVerifier.create(Flux.just("a", "b"))
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();


        StepVerifier.withVirtualTime(() -> Flux.interval(Duration.ofHours(4), Duration.ofDays(1)).take(2))
                .expectSubscription()
                .expectNoEvent(Duration.ofHours(4))
                .expectNext(0L)
                .thenAwait(Duration.ofDays(1))
                .expectNext(1L)
                .verifyComplete();


        final TestPublisher<String> testPublisher = TestPublisher.create();
        testPublisher.next("a");
        testPublisher.next("b");
        testPublisher.complete();

        StepVerifier.create(testPublisher)
                .expectNext("a")
                .expectNext("b")
                .expectComplete();

//        Hooks.onOperator(providedHook -> providedHook.operatorStacktrace());
        Hooks.onOperatorDebug();
    }

    @Test
    public void checkPointTest() {
        Flux.just(1, 0).map(x -> 1 / x).checkpoint("test").subscribe(System.out::println);
    }


    @Test
    public void logTest() {
        Flux.range(1, 2).log("Range").subscribe(System.out::println);
    }

    @Test
    public void hotTest() {

        final Flux<Long> source = Flux.interval(Duration.ofMillis(1000))
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        source
                .toStream()
                .forEach(System.out::println);
    }


    private class BusinessException extends Throwable {
        public BusinessException(String sla_exceeded, Throwable original) {
        }
    }
}
