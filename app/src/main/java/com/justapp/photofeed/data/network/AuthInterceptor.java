package com.justapp.photofeed.data.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Реализация {@link Interceptor}, предназначенная для подстановки токена в заголовок запроса
 *
 * @author Sergey Rodionov
 */
public class AuthInterceptor implements Interceptor {

    private static final String TOKEN_TYPE = "OAuth";
    private static final String HEADER = "Authorization";
    private String mToken = "";

    /**
     * {@inheritDoc}
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String formatHeader = String.format("%s %s", TOKEN_TYPE, mToken);
        request = request.newBuilder()
                .addHeader(HEADER, formatHeader)
                .build();
        return chain.proceed(request);
    }

    /**
     * Проставляем токен, полученный после предоставления прав доступа пользователем
     *
     * @param token пользовательский токен
     */
    public void setToken(@NonNull String token) {
        mToken = token;
    }

}
