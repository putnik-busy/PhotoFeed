package com.justapp.photofeed.routers.photoview;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.justapp.photofeed.R;
import com.justapp.photofeed.presentation.photoview.fragment.PhotoViewFragment;
import com.justapp.photofeed.utils.ActivityUtils;

/**
 * @author Sergey Rodionov
 */
public class PhotoViewRouterImpl implements PhotoViewRouter {

    @Override
    public void startPhotoView(@NonNull FragmentActivity activity, @NonNull String url) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        ActivityUtils.replaceFragmentInActivity(fragmentManager,
                PhotoViewFragment.newInstance(url),
                R.id.container, PhotoViewFragment.TAG);
    }
}
