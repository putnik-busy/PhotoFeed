package com.justapp.photofeed.routers.photoview;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

/**
 * @author Sergey Rodionov
 */
public interface PhotoViewRouter {

    void startPhotoView(@NonNull FragmentActivity activity, @NonNull String url);
}
