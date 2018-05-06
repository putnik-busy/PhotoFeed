package com.justapp.photofeed.presentation.feed.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.presentation.base.BaseView;

import java.util.List;

/**
 * @author Sergey Rodionov
 */
public interface PhotoView extends BaseView {

    @StateStrategyType(SkipStrategy.class)
    void showProgress(boolean loading);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showPhotos(List<ItemModel> list);

    void showEmpty();

    void showErrorMessage(String message);

}
