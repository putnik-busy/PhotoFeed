package com.justapp.photofeed.di.application;

import com.justapp.photofeed.rx.RxSchedulers;
import com.justapp.photofeed.rx.RxSchedulersImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Sergey Rodionov
 */
@Module
public interface RxModule {

    @Binds
    RxSchedulers provideRxSchedulers(RxSchedulersImpl rxSchedulers);
}
