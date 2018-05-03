package com.justapp.photofeed.domain.repository;

import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public interface AuthRepository {

    void saveToken(@NonNull String token);

    boolean hasToken();
}
