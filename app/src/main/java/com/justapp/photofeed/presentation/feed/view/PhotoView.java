package com.justapp.photofeed.presentation.feed.view;

import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.presentation.base.BaseView;

import java.util.List;

/**
 * @author Sergey Rodionov
 */
public interface PhotoView extends BaseView {

    void showProgress(boolean loading);

    void showPhotos(List<ItemModel> list);

    void showEmpty(boolean isEmpty);

    void showErrorMessage(String message);

    void logout();
}
