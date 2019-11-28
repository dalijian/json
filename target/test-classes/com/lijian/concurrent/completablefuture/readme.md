##completionStage 
1. runAsync(Runnable runnable) 无返回值
2. supplyAsync(Supplier<U> supplier)有返回值
###1. 串行关系
1. thenApply 参数 function
2. thenAccept 参数 consumer
3. thenRun  参数 runnable
4. thenCompose 参数 与 thenApply 相同，会新创建出一个子流程
###2. and 汇聚关系
1. thenCombine
   1. public<U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> f)
   2. Returns a new CompletionStage that, when this and the other given stage both complete normally, is executed with the two results as arguments to the supplied function.
   3. 有返回值 **function**
2. thenAcceptBoth
    1. public <U> CompeltableFuture<Void> thenAcceptBoth(CompletionStage<? extends U>other,BiConsumer<? super T,? super U> action)
    2.  when this and the other given stage both complete normally, is executed with the two results as arguments to the supplied action. 
    3. 无返回值 **consumer**
3. runAfterBoth
    1. public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other,Runnable action)
    2. Returns a new CompletionStage that, when this and the other given stage both complete normally, executes the given action.
    3. 无返回值，不接受参数
###3. or 汇聚关系
1. applyToEither
    1. public <U> Completable<U> applyToEither(CompletionStage<? extends T> other,Function<? super T,U>fn)
    2. Returns a new CompletionStage that, when either this or the other given stage complete normally, is executed with the corresponding result as argument to the supplied function. 
    3. 有返回值，有参数
2. acceptEither
    1. public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action)
    2. Returns a new CompletionStage that, when either this or the other given stage complete normally, is executed with the corresponding result as argument to the supplied action. 
    3. 无返回值，有参数
3. runAfterEither
    1. public CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action)
    2. Returns a new CompletionStage that, when either this or the other given stage complete normally, executes the given action. 
    3. 无返回值，无参数
###4. 异常处理
1. exceptionally() 处理异常，类似catch{}
    1. public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
    2. Returns a new CompletableFuture that is completed when this CompletableFuture completes, with the result of the given function of the exception triggering this CompletableFuture's completion when it completes exceptionally; otherwise, if this CompletableFuture completes normally, then the returned CompletableFuture also completes normally with the same value.
    3. 捕捉异常处理 ，支持返回结果
2. whenComplete()不支持返回结果,handle() 支持返回结果的 类似于finally{}
    1. public CompeltableFuture<T> whenComplete(BiConsumer<? super T,?super Throwable> action)
    2. 无返回值
3. CompletionStage<R> whenCompleteAsync(consumer);
4. CompletionStage<R> handle(fn);
    1. public <U> CompletableFuture<U> handle(BiFunction<? super T,Throwable,?extends U> fn)
    2. 有返回值
5. CompletionStage<R> handleAsync(fn);


###异步转同步
1. 在主线程 调用子线程 thread.join()
2. 使用 countDownLatch,cycleBarries,semphore
3. 使用FutureTask(runnable),或者FutureTask(callable) 封装任务，调用get方法。
4. executor.submit(callable) 或 executor.execute(runnable) 返回 Future 对象，调用get() 阻塞线程
5. CompleteFuture   使用 join() 异步转同步

###Future
1. boolean cancel(boolean mayInterruptIfRunning);
    1. 取消任务

2. boolean isCancelled();
    1. 判读任务是否取消

3. boolean isDone();
    1. 判断任务是否已结束

4. get();
    1. 获得任务执行结果

5. get(long timeout,TimeUnit unit);
     1. 获得任务执行结果，支持超时