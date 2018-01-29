package com.juanjo.juanjo.domain.interactor;

import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;

import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by juanj on 11/01/2018.
 */

abstract class AbstractUseCase {
    private final ThreadExecutor threadExecutor;
    private final MainThread mainThread;
    private final CompositeDisposable disposables;

    AbstractUseCase(ThreadExecutor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
        disposables = new CompositeDisposable();
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

    public ThreadExecutor getThreadExecutor() {
        return threadExecutor;
    }

    public MainThread getMainThread() {
        return mainThread;
    }
}
