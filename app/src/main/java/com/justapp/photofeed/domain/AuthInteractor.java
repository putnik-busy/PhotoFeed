package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.justapp.photofeed.di.scope.Auth;
import com.justapp.photofeed.domain.repository.AuthRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Интерактор аутентификации пользователя
 *
 * @author Sergey Rodionov
 */
@Auth
public class AuthInteractor {

    private static final String PATTERN_REGEX = "access_token=(.*?)(&|$)";
    private final AuthRepository mAuthRepository;

    /**
     * Конструктор для {@link AuthInteractor}
     *
     * @param authRepository репозиторий аутентификации пользователя
     */
    @Inject
    AuthInteractor(@NonNull AuthRepository authRepository) {
        mAuthRepository = authRepository;
    }

    /**
     * Сохраняет токен пользователя, полученный от сервера после аутентификации
     * <p>
     * Внутри парсит ответ с сервера, содержащий токен, и сохраняет в репозитории
     *
     * @param data ответ с сервера содержащий токен
     */
    public void saveToken(@NonNull String data) {
        String token = parseToken(data);
        if (token != null) {
            mAuthRepository.saveToken(token);
        }
    }

    /**
     * Проверяет наличие токена в репозитории
     *
     * @return {@code true} если токен в репозитории, {@code false} в противном случае
     */
    public boolean hasToken() {
        return mAuthRepository.hasToken();
    }

    /**
     * Выполняет очистку токена пользователя
     */
    void onLogoff() {
        mAuthRepository.onLogoff();
    }

    @Nullable
    private String parseToken(String data) {
        String token = null;
        Pattern pattern = Pattern.compile(PATTERN_REGEX);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            token = matcher.group(1);
        }
        return token;
    }

}
