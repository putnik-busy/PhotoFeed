package com.justapp.photofeed.data.keystore;

import android.support.annotation.NonNull;

/**
 * Менеджер для работы с хранилищем ключей. Производит их шифрование/дешифрование.
 * <p>
 * Работает с API 18
 *
 * @author Sergey Rodionov
 */
public interface KeyStoreManager {

    /**
     * Сохраняет токен в хранилище ключей в шифрованном видел
     *
     * @param token токен, полученный при авторизации
     */
    void saveToken(@NonNull String token);

    /**
     * @return токен из хранилища в дешифрованном виде
     */
    @NonNull
    String getToken();

    /**
     * Очищает хранилище ключей
     */
    void deleteToken();

}
