package com.justapp.photofeed.routers.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.justapp.photofeed.presentation.feed.activity.FeedActivity;

/**
 * @author Sergey Rodionov
 */
public class AuthRouterImpl implements AuthRouter {

    @Override
    public void startAuth(@NonNull Activity activity, @NonNull String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void startPhotoFeed(@NonNull Activity activity) {
        Intent intent = new Intent(activity, FeedActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
