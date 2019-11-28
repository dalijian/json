##Executor 框架组成
1. 任务 包括被执行任务需要实现的接口：Runnable接口或Callable接口
    1. 区别 是 Runnable不会返回结果，而Callable可以返回结果。
    2. Callable<Object> Executors.callable(Runnable task) 工厂类Executors可以把一个Runnable包装成一个Callable。Future.get() 返回null
    3. Callable<T> callable(Runnable task,T result) 把一个Runnable和一个待返回的结果包装成一个Callable, FutureTask.get() 返回result对象
2. 任务的执行 包括任务执行机制的核心接口Executor,以及继承自Executor的ExecutorService接口，
    1. ThreadPoolExecutor 使用 工厂类Executors类创建    
        1. SingleThreadExecutor 创建使用单个线程 适用于需要保证顺序性的执行各个任务，并且在任意时间点，不会有多个线程是活动的应用场景
            1. 使用无界队列LinkedBlickingQueue 作为线程池的工作队列
        2. FixedThreadPool 创建固定线程数 适用于需要限制当前线程数量的应用场景，适用于负载比较重的服务器
            1. 使用无界队列LinkedBlickingQueue 作为线程池的工作队列
        3. CachedThreadPool 创建一个会根据需要创建新线程 ,是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器
            1. CachedThreadPool 使用没有容量的SynchronousQueue作为线程池的工作队列，但是CachedThreadPool的maximumPool是无界的。
            2. 如果主线程提交任务的速度高于maximumPool中线程处理任务的速度时，CachedThreadPool会不断创建新线程。
            3. 极端情况下CachedThreadPool 会因为创建过多线程而耗尽CPU和内存资源。
    2. ScheduledThreadPoolExecutor
        1. ScheduledThreadPoolExecutor 包含若干个线程
            1. 使用DelayQueue 是一个无界队列
        2. SingleThreadScheduledExecutor 只包含一个线程的scheduledThreadPoolExecutor
3. 异步计算的结果。包括接口Future和实现Future接口的FutureTask类。
    1. FutureTask 除了实现Future 接口外，还实现了Runnable接口，因此FutureTask可以交给Executor执行，也可以由线程直接执行FutureTask.run()
4. shutdown() 与shutdownNow()
    1. 当线程池调用shutdown() 方法时，线程池的状态则立刻变成shutdown,此时不能再往线程池中添加任何任务，否则会抛出RejectedExecutionException异常，但是 线程池不会立刻退出，直到线程池中的任务都处理完成，才会退出。
    2. shutdownNow()方法是使线程池的状态立刻变成stop状态，并试图停止所有正在执行的线程（如果有if判断则人为地抛出异常)不在处理还在池队列中等待的任务，还会返回那些未执行的任务。
