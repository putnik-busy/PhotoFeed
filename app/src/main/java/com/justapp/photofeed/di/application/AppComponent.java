package com.justapp.photofeed.di.application;

import android.app.Application;

import com.justapp.photofeed.di.data.DataComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author Sergey Rodionov
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        RxModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    DataComponent.Builder createDataComponent();

}
