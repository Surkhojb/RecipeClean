package com.juanjo.juanjo.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by juanj on 11/01/2018.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override public void onNext(T t) {
        // no-op by default.
    }

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }

    public void onTerminate() {

    }
}