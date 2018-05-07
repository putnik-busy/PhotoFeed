package com.justapp.photofeed.data.repository;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.domain.repository.AuthRepository;

/**
 * Реализация {@link AuthRepository}
 *
 * @author Sergey Rodionov
 */
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthInterceptor mAuthInterceptor;
    private final KeyStoreManager mKeyStoreManager;

    /**
     * Конструктор {@link AuthRepositoryImpl}
     *
     * @param authInterceptor Interceptor авторизации
     * @param keyStoreManager Менеджер для работы хранилищем ключей
     */
    public AuthRepositoryImpl(@NonNull AuthInterceptor authInterceptor,
                              @NonNull KeyStoreManager keyStoreManager) {
        mAuthInterceptor = authInterceptor;
        mKeyStoreManager = keyStoreManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveToken(@NonNull String token) {
        mKeyStoreManager.saveToken(token);
        mAuthInterceptor.setToken(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasToken() {
        String token = mKeyStoreManager.getToken();
        return !TextUtils.isEmpty(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLogoff() {
        mKeyStoreManager.deleteToken();
    }

}
