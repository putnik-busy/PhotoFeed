package com.justapp.photofeed.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Sergey Rodionov
 */
public class RxSchedulersImpl implements RxSchedulers {

    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

}
