package com.justapp.photofeed.di.auth;

import com.justapp.photofeed.di.scope.AuthScope;
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
    public AuthRouter provideAuthRouter() {
        return new AuthRouterImpl();
    }

}
