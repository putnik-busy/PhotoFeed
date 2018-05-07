package com.justapp.photofeed.di.application;

import android.app.Application;

import com.justapp.photofeed.PhotoFeedApp;
import com.justapp.photofeed.di.auth.AuthComponent;
import com.justapp.photofeed.di.data.DataComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Основной компонент {@link Component} приложения.
 * <p>
 * Содержит модули, необходимые для работы на протяжении приложения
 *
 * @author Sergey Rodionov
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        RxModule.class,
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(PhotoFeedApp photoFeedApp);

    DataComponent.Builder createDataComponent();

    AuthComponent.Builder createAuthComponent();

}
