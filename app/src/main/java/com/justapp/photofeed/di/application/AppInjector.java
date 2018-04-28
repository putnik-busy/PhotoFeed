package com.justapp.photofeed.di.application;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public class AppInjector {

    private static AppComponent mAppComponent;

    public static void createAppComponent(@NonNull Application application) {
        mAppComponent = DaggerAppComponent.builder()
                .application(application)
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
