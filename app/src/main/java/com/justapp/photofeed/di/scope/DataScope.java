package com.justapp.photofeed.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Sergey Rodionov
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {
}
