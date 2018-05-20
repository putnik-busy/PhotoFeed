package com.justapp.photofeed.presentation.feed.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.presentation.base.BaseView;

import java.util.List;

/**
 * Вью ленты фотографий
 *
 * @author Sergey Rodionov
 */
public interface PhotoView extends BaseView {

    /**
     * Показать прогресс загрузки
     *
     * @param loading {@code true} если загрузка идет, {@code false} иначе
     */
    @StateStrategyType(SkipStrategy.class)
    void showProgress(boolean loading);

    /**
     * Отобразить фото
     *
     * @param list список фото для отображения
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showPhotos(List<ImageModel> list);

    /**
     * Показать заглушку, например, в случае ошибки или отсутствия данных
     */
    void showEmpty();

    /**
     * Показать сообщение об ошибке
     *
     * @param message ресурс с текстом ошибки
     */
    void showErrorMessage(@NonNull String message);

}
