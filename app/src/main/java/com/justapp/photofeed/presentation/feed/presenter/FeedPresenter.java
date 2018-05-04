package com.justapp.photofeed.presentation.feed.presenter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.domain.DiskInteractor;
import com.justapp.photofeed.models.local.disk.resources.ImageListModel;
import com.justapp.photofeed.presentation.base.BasePresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.justapp.photofeed.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Sergey Rodionov
 */
@InjectViewState
public class FeedPresenter extends BasePresenter<PhotoView> {

    private DiskInteractor mDiskInteractor;
    private RxSchedulers mRxSchedulers;
    private KeyStoreManager mKeyStoreManager;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public FeedPresenter(@NonNull DiskInteractor diskInteractor,
                         @NonNull RxSchedulers rxSchedulers) {
        mDiskInteractor = diskInteractor;
        mRxSchedulers = rxSchedulers;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    public void loadPhotos(int limit, int offset) {
        if (offset == 0) {
            getViewState().showProgress(true);
        }
        mCompositeDisposable.add(mDiskInteractor
                .loadPhotos(limit, offset)
                .subscribeOn(mRxSchedulers.getIOScheduler())
                .observeOn(mRxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(__ -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribeWith(new DisposableObserver<ImageListModel>() {
                    @Override
                    public void onNext(ImageListModel imageListModel) {
                        getViewState().showPhotos(imageListModel.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showErrorMessage("");
                        mCompositeDisposable.remove(this);
                    }

                    @Override
                    public void onComplete() {
                        mCompositeDisposable.remove(this);
                    }
                }));

    }
}
