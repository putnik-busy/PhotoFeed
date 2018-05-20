package com.justapp.photofeed.di.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreHelper;
import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.keystore.KeyStoreManagerImpl;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.presentation.resources.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Основной модуль {@link Module} приложения.
 * <p>
 * Провайдит зависимости, необходимые на протяжении жизни приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    KeyStoreManager provideKeyStoreManager(@NonNull Context context,
                                                  @NonNull KeyStoreHelper helper) {
        return new KeyStoreManagerImpl(context, helper);
    }

    @Singleton
    @Provides
    AuthInterceptor provideAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Singleton
    @Provides
    ResourceManager provideResourceManager(@NonNull Context context) {
        return new ResourceManager(context);
    }

}
