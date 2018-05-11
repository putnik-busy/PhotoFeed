package com.justapp.photofeed.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп {@link Scope} для работы до авторизации в приложении
 *
 * @author Sergey Rodionov
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
}
