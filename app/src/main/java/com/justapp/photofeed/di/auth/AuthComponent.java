package com.justapp.photofeed.di.auth;

import com.justapp.photofeed.di.scope.AuthScope;
import com.justapp.photofeed.presentation.auth.activity.AuthActivity;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Авторизационный компонент {@link Component} приложения.
 * <p>
 * Содержит модули, необходимые для авторизации
 *
 * @author Sergey Rodionov
 */
@AuthScope
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent {

    @Subcomponent.Builder
    interface Builder {
        AuthComponent build();
    }

    void inject(AuthActivity activity);

}
