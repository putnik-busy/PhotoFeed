package com.justapp.photofeed.di.auth;

import com.justapp.photofeed.di.application.AppInjector;

/**
 * @author Sergey Rodionov
 */
public class AuthInjector {

    private static AuthComponent sAuthComponent;

    public static AuthComponent createAuthComponent() {
        if (sAuthComponent == null) {
            sAuthComponent = AppInjector.getAppComponent()
                    .createAuthComponent()
                    .build();
        }
        return sAuthComponent;
    }

    public static void clearAuthModule() {
        sAuthComponent = null;
    }

}
