package com.justapp.photofeed.di.auth;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.data.repository.AuthRepositoryImpl;
import com.justapp.photofeed.di.scope.Auth;
import com.justapp.photofeed.domain.repository.AuthRepository;
import com.justapp.photofeed.routers.auth.AuthRouter;
import com.justapp.photofeed.routers.auth.AuthRouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Авторизационный модуль {@link Module} приложения.
 * <p>
 * Провайдит зависимости, необходимые для авторизации
 *
 * @author Sergey Rodionov
 */
@Module
public class AuthModule {

    @Auth
    @Provides
    AuthRepository provideAuthRepository(@NonNull AuthInterceptor authInterceptor,
                                         @NonNull KeyStoreManager keyStoreManager) {
        return new AuthRepositoryImpl(authInterceptor, keyStoreManager);
    }

    @Auth
    @Provides
    AuthRouter provideAuthRouter() {
        return new AuthRouterImpl();
    }

}
