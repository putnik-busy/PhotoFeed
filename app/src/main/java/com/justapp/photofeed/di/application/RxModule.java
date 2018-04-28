package com.justapp.photofeed.di.application;

import com.justapp.photofeed.utils.rx.RxSchedulers;
import com.justapp.photofeed.utils.rx.RxSchedulersImpl;

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
