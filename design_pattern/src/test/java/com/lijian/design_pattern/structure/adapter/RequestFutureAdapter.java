package com.lijian.design_pattern.structure.adapter;

import java.util.concurrent.Future;

/**
 * Adapt from a request future of one type to another.
 *
 * @param <F> Type to adapt from
 * @param <T> Type to adapt to
 */
public abstract class RequestFutureAdapter<F, T> {

    public abstract void onSuccess(F value, Future<T> future);

//    public void onFailure(RuntimeException e, T  future) {
//        future.raise(e);
//    }
}