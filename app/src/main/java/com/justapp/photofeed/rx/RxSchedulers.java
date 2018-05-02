package com.justapp.photofeed.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * @author Sergey Rodionov
 */
public interface RxSchedulers {

    @NonNull
    Scheduler getMainThreadScheduler();

    @NonNull
    Scheduler getIOScheduler();
}
