package com.justapp.photofeed;

import android.app.Application;

import com.justapp.photofeed.di.application.AppInjector;

/**
 * @author Sergey Rodionov
 */
public class PhotoFeedApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.createAppComponent(this);
        AppInjector.getAppComponent().inject(this);
    }

}
