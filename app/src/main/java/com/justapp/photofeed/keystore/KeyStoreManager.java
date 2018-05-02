package com.justapp.photofeed.keystore;

import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public interface KeyStoreManager {

    void saveToken(String token);

    @NonNull
    String getToken();

    void deleteToken();

}
