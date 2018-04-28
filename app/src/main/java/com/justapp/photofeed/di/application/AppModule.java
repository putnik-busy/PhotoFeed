package com.justapp.photofeed.di.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Rodionov
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }
}
