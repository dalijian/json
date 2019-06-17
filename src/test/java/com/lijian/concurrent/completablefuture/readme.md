##completionStage 
###1. 串行关系
1. thenApply 参数 function
2. thenAccept 参数 consumer
3. thenRun  参数 runnable
4. thenCompose 参数 与 thenApply 相同，会新创建出一个子流程
###2. and 汇聚关系
1. thenCombine
2. thenAcceptBoth
3. runAfterBoth
###3. or 汇聚关系
1. applyToEither
2. acceptEither
3. runAfterEither
###4. 异常处理
1. exceptionally() 处理异常，类似catch{}
2. whenComplete()不支持返回结果,handle() 支持返回结果的 类似于finally{}
3. CompletionStage<R> whenCompleteAsync(consumer);
4. CompletionStage<R> handle(fn);
5. CompletionStage<R> handleAsync(fn);