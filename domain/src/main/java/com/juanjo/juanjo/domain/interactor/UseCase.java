package com.juanjo.juanjo.domain.interactor;

import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.google.common.base.Preconditions;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by juanj on 11/01/2018.
 */

abstract class UseCase<T, Params> extends AbstractUseCase {
    UseCase(ThreadExecutor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(DefaultObserver observer, Params params) {
        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(getThreadExecutor()))
                .observeOn(getMainThread().getScheduler(), true)
                .doAfterTerminate(observer::onTerminate);

        addDisposable(observable.subscribeWith(observer));
    }
}
