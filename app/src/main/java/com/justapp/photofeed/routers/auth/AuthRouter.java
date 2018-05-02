package com.justapp.photofeed.routers.auth;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public interface AuthRouter {

    void startAuth(@NonNull Activity activity, @NonNull String url);

    void startPhotoFeed(@NonNull Activity activity);
}
