package com.lijian.concurrent.memorizer;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer3<A,V> implements Computable<A,V> {

    private final ConcurrentMap<A,Future<V>>  cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws InterruptedException {
        while (true) {


            Future<V> result = cache.get(arg);
            if (result == null) {
                Callable<V> eval = () -> {
                    return c.compute(arg);
                };
                FutureTask<V> futureTask = new FutureTask<>(eval);


                result = cache.putIfAbsent(arg, result);
                if (result == null) {
                    result = futureTask;
                    futureTask.run();
                }
            }
            try {
                return result.get();
            } catch (CancellationException e) {
                cache.remove(arg, false);

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
