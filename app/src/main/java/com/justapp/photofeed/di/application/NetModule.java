package com.justapp.photofeed.di.application;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.network.AuthInterceptor;
import com.justapp.photofeed.data.network.LoggingInterceptor;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.rx.RxSchedulers;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Сетевой модуль {@link Module} приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class NetModule {

    private static final String BASE_URL = "https://cloud-api.yandex.net/";
    private static final int TIMEOUT = 20;

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(@NonNull KeyStoreManager keyStoreManager,
                                     @NonNull AuthInterceptor interceptor) {
        interceptor.setToken(keyStoreManager.getToken());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS);

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@NonNull OkHttpClient client, @NonNull Gson gson, @NonNull RxSchedulers schedulers) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.getIOScheduler()))
                .build();
    }

    @Singleton
    @Provides
    Gson provideGSON() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class,
                        (JsonDeserializer<Date>) (json, typeOfT, context)
                                -> new Date(json.getAsJsonPrimitive().getAsLong()))
                .create();
    }

    @Singleton
    @Provides
    RestApi provideRestApi(@NonNull Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Singleton
    @Provides
    Picasso providePicasso(@NonNull Context context,
                           @NonNull OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .loggingEnabled(false)
                .indicatorsEnabled(false)
                .build();
    }

}
