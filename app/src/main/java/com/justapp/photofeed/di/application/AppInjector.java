package com.justapp.photofeed.di.application;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public class AppInjector {

    private static AppComponent mAppComponent;

    public static AppComponent createAppComponent(@NonNull Application application) {
        mAppComponent = DaggerAppComponent.builder()
                .application(application)
                .build();
        return mAppComponent;
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
