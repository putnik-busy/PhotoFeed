package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;

import com.justapp.photofeed.di.scope.AuthScope;
import com.justapp.photofeed.domain.repository.AuthRepository;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@AuthScope
public class AuthInteractor {

    private final AuthRepository mAuthRepository;

    @Inject
    public AuthInteractor(@NonNull AuthRepository authRepository) {
        mAuthRepository = authRepository;
    }

    public void saveToken(@NonNull String token) {
        mAuthRepository.saveToken(token);
    }

    public boolean hasToken() {
        return mAuthRepository.hasToken();
    }

}
