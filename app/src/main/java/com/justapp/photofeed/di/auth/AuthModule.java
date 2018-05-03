package com.justapp.photofeed.di.auth;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.data.repository.AuthRepositoryImpl;
import com.justapp.photofeed.di.scope.AuthScope;
import com.justapp.photofeed.domain.repository.AuthRepository;
import com.justapp.photofeed.routers.auth.AuthRouter;
import com.justapp.photofeed.routers.auth.AuthRouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Rodionov
 */
@Module
public class AuthModule {

    @AuthScope
    @Provides
    public AuthRepository provideAuthRepository(@NonNull AuthInterceptor authInterceptor,
                                                @NonNull KeyStoreManager keyStoreManager) {
        return new AuthRepositoryImpl(authInterceptor, keyStoreManager);
    }

    @AuthScope
    @Provides
    public AuthRouter provideAuthRouter() {
        return new AuthRouterImpl();
    }

}
