package com.justapp.photofeed.presentation.auth.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.keystore.KeyStoreManager;
import com.justapp.photofeed.presentation.auth.view.AuthView;
import com.justapp.photofeed.presentation.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@InjectViewState
public class AuthPresenter extends BasePresenter<AuthView> {

    private static final String AUTH_URL = "https://oauth.yandex.ru/authorize?response_type=token&client_id=5c653ccd24cd43a28c82b24d91ffef0e";

    private KeyStoreManager mKeyStoreManager;
    private AuthInterceptor mAuthInterceptor;

    public AuthPresenter() {

    }

    @Inject
    public AuthPresenter(@NonNull KeyStoreManager keyStoreManager,
                         @NonNull AuthInterceptor authInterceptor) {
        mKeyStoreManager = keyStoreManager;
        mAuthInterceptor = authInterceptor;
    }

    public void enter() {
        getViewState().enableAuthButton(false);
        String token = mKeyStoreManager.getToken();
        if (!TextUtils.isEmpty(token)) {
            getViewState().startPhotoFeed();
        } else {
            getViewState().startAuth(AUTH_URL);
        }
    }

    public void saveToken(@NonNull String token) {
        getViewState().enableAuthButton(true);
        mAuthInterceptor.setToken(token);
        mKeyStoreManager.saveToken(token);
        getViewState().startPhotoFeed();
    }
}
