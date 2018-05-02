package com.justapp.photofeed.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Базовый презентер для приложения.
 * <p>
 * Тут будет какая-то общая логика для всех презентеров в приложении.
 *
 * @param <View> интерфейс для представления
 * @author Sergey Rodionov
 */
public class BasePresenter<View extends BaseView> extends MvpPresenter<View> {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Возвращает {@link CompositeDisposable}, который очистит ссылки на {@link #onDestroy()}.
     *
     * @return {@link CompositeDisposable}
     */
    public final CompositeDisposable getRxCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

}
