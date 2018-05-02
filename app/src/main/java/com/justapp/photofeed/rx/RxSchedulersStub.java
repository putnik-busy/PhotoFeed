package com.justapp.photofeed.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Sergey Rodionov
 */
public class RxSchedulersStub implements RxSchedulers {

    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

}
