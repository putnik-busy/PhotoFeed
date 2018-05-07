package com.justapp.photofeed.domain.repository;

import android.support.annotation.NonNull;

/**
 * Репозиторий аутентификации пользователя
 *
 * @author Sergey Rodionov
 */
public interface AuthRepository {

    /**
     * Сохраняет токен пользователя, полученный от сервера после аутентификации
     *
     * @param token токен пользователя
     */
    void saveToken(@NonNull String token);

    /**
     * @return {@code true} если токен получен и лежит в хранилище, {@code false} в противном случае
     */
    boolean hasToken();

    /**
     * Выполняет очистку токена пользователя
     */
    void onLogoff();

}
