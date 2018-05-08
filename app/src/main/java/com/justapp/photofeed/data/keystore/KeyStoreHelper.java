package com.justapp.photofeed.data.keystore;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Хелпер для работы с {@link SharedPreferences} хранилищем ключей
 *
 * @author Sergey Rodionov
 */
@Singleton
public final class KeyStoreHelper {

    private static final String SHARED_PREF_NAME = "KEYSTORE_SETTING";
    private static final String PREF_KEY_AES = "PREF_KEY_AES";
    private static final String PREF_KEY_IV = "PREF_KEY_IV";
    private static final String KEY_TOKEN = "TOKEN";

    private SharedPreferences mSharedPreferences;

    @Inject
    public KeyStoreHelper(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    private String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    private void putString(String key, String value) {
        mSharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public String getIV() {
        return getString(PREF_KEY_IV);
    }

    public void setIV(String value) {
        putString(PREF_KEY_IV, value);
    }

    public String getAESKey() {
        return getString(PREF_KEY_AES);
    }

    public void setAESKey(String value) {
        putString(PREF_KEY_AES, value);
    }

    public void setToken(String token) {
        putString(KEY_TOKEN, token);
    }

    public String getToken() {
        return getString(KEY_TOKEN);
    }

    public void clearToken() {
        mSharedPreferences.edit().clear().apply();
    }

}
