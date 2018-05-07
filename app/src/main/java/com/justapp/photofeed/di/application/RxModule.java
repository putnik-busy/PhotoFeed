package com.justapp.photofeed.di.application;

import com.justapp.photofeed.rx.RxSchedulers;
import com.justapp.photofeed.rx.RxSchedulersImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Rx модуль {@link Module} приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class RxModule {

    @Singleton
    @Provides
    public RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }
}
