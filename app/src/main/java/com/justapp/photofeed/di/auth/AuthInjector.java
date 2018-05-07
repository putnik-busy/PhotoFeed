package com.justapp.photofeed.di.auth;

import com.justapp.photofeed.di.application.AppInjector;

/**
 * Инжектор для компонента {@link AuthComponent}
 *
 * @author Sergey Rodionov
 */
public class AuthInjector {

    private AuthInjector() {
        throw new UnsupportedOperationException("Не надо создавать экземпляр класса");
    }

    private static AuthComponent sAuthComponent;

    /**
     * Инициализация {@link AuthComponent} компонента
     *
     * @return {@link AuthComponent}
     */
    public static AuthComponent createAuthComponent() {
        if (sAuthComponent == null) {
            sAuthComponent = AppInjector.getAppComponent()
                    .createAuthComponent()
                    .build();
        }
        return sAuthComponent;
    }

    /**
     * Очищает {@link AuthComponent}
     */
    public static void clearAuthModule() {
        sAuthComponent = null;
    }

}
