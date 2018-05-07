package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.justapp.photofeed.di.scope.AuthScope;
import com.justapp.photofeed.domain.repository.AuthRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void saveToken(@NonNull String data) {
        String token = parseToken(data);
        if (token != null) {
            mAuthRepository.saveToken(token);
        }
    }

    public boolean hasToken() {
        return mAuthRepository.hasToken();
    }

    public void onLogoff() {
        mAuthRepository.onLogoff();
    }

    @Nullable
    private String parseToken(String data) {
        String token = null;
        Pattern pattern = Pattern.compile("access_token=(.*?)(&|$)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            token = matcher.group(1);
        }
        return token;
    }

}
