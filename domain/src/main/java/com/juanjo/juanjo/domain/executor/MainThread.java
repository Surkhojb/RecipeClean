package com.juanjo.juanjo.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by juanj on 11/01/2018.
 */

public interface MainThread {
    Scheduler getScheduler();
}
