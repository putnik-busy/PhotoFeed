package com.justapp.photofeed.presentation.feed.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.domain.DiskInteractor;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.presentation.base.BasePresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.justapp.photofeed.rx.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Sergey Rodionov
 */
@InjectViewState
public class FeedPresenter extends BasePresenter<PhotoView> {

    private final DiskInteractor mDiskInteractor;
    private final RxSchedulers mRxSchedulers;

    @Inject
    public FeedPresenter(@NonNull DiskInteractor diskInteractor,
                         @NonNull RxSchedulers rxSchedulers) {
        mDiskInteractor = diskInteractor;
        mRxSchedulers = rxSchedulers;
    }

    public void loadPhotos(int limit, int offset) {
        mDiskInteractor
                .loadPhotos(limit, offset)
                .subscribeOn(mRxSchedulers.getIOScheduler())
                .observeOn(mRxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(__ -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(new DisposableSingleObserver<List<ImageModel>>() {

                    @Override
                    public void onSuccess(List<ImageModel> imageModels) {
                        if (imageModels.isEmpty()) {
                            getViewState().showEmpty();
                        } else {
                            getViewState().showPhotos(imageModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showErrorMessage("Ошибка загрузки");
                    }
                });
    }
}
