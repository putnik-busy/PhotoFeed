package com.justapp.photofeed.di.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreHelper;
import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.keystore.KeyStoreManagerImpl;
import com.justapp.photofeed.data.network.AuthInterceptor;

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

    @Singleton
    @Provides
    public KeyStoreManager provideKeyStoreManager(@NonNull Context context,
                                                  @NonNull KeyStoreHelper helper) {
        return new KeyStoreManagerImpl(context, helper);
    }

    @Singleton
    @Provides
    AuthInterceptor provideAuthInterceptor() {
        return new AuthInterceptor();
    }

}
