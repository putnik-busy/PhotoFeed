package com.justapp.photofeed.presentation.auth.view;

import com.justapp.photofeed.presentation.base.BaseView;

/**
 * @author Sergey Rodionov
 */
public interface AuthView extends BaseView {

    void startAuth(String url);

    void enableAuthButton(boolean enable);

    void startPhotoFeed();
}
