package com.justapp.photofeed.data.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.justapp.photofeed.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Sergey Rodionov
 */
public class LoggingInterceptor implements Interceptor {

    private static final String TAG = "LoggingInterceptor";

    private static LoggingInterceptor sLoggingInterceptor;
    private final HttpLoggingInterceptor mHttpLoggingInterceptor;

    public LoggingInterceptor() {
        mHttpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.v(TAG, message)
        ).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
    }

    public static LoggingInterceptor getLoggingInterceptor() {
        if (sLoggingInterceptor == null) {
            sLoggingInterceptor = new LoggingInterceptor();
        }
        return sLoggingInterceptor;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return mHttpLoggingInterceptor.intercept(chain);
    }

}
