package com.justapp.photofeed.presentation.auth.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.domain.AuthInteractor;
import com.justapp.photofeed.presentation.auth.view.AuthView;
import com.justapp.photofeed.presentation.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@InjectViewState
public class AuthPresenter extends BasePresenter<AuthView> {

    private static final String AUTH_URL = "https://oauth.yandex.ru/authorize?response_type=token&client_id=5c653ccd24cd43a28c82b24d91ffef0e";

    private final AuthInteractor mAuthInteractor;

    @Inject
    public AuthPresenter(@NonNull AuthInteractor authInteractor) {
        mAuthInteractor = authInteractor;
    }

    public void enter() {
        getViewState().enableAuthButton(false);
        if (mAuthInteractor.hasToken()) {
            getViewState().startPhotoFeed();
        } else {
            getViewState().startAuth(AUTH_URL);
        }
    }

    public void saveToken(@NonNull String token) {
        getViewState().enableAuthButton(true);
        mAuthInteractor.saveToken(token);
        getViewState().startPhotoFeed();
    }
}
