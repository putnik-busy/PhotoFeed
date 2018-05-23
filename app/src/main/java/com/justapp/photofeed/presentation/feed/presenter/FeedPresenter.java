package com.justapp.photofeed.presentation.feed.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.R;
import com.justapp.photofeed.di.scope.Data;
import com.justapp.photofeed.domain.DiskInteractor;
import com.justapp.photofeed.presentation.base.BasePresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.justapp.photofeed.presentation.resources.ResourceManager;
import com.justapp.photofeed.rx.RxSchedulers;

import javax.inject.Inject;

import static com.justapp.photofeed.presentation.feed.fragment.FeedFragment.PAGINATION_ITEMS_COUNT;

/**
 * Презентер ленты фотографий
 *
 * @author Sergey Rodionov
 */
@Data
@InjectViewState
public class FeedPresenter extends BasePresenter<PhotoView> {

    private final DiskInteractor mDiskInteractor;
    private final RxSchedulers mRxSchedulers;
    private final ResourceManager mResourceManager;

    /**
     * Конструктор для {@link FeedPresenter}
     *
     * @param diskInteractor  интерактор для операций над фото
     * @param rxSchedulers    планировщик потоков
     * @param resourceManager менеджер ресурсов
     */
    @Inject
    public FeedPresenter(@NonNull DiskInteractor diskInteractor,
                         @NonNull RxSchedulers rxSchedulers,
                         @NonNull ResourceManager resourceManager) {
        mDiskInteractor = diskInteractor;
        mRxSchedulers = rxSchedulers;
        mResourceManager = resourceManager;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPhotos(PAGINATION_ITEMS_COUNT, 0);
    }

    /**
     * Загружает фото пользователя
     *
     * @param limit  количество элементов на странице
     * @param offset смещение от начала списка
     */
    public void loadPhotos(int limit, int offset) {
        getRxCompositeDisposable().add(mDiskInteractor
                .loadPhotos(limit, offset)
                .subscribeOn(mRxSchedulers.getIOScheduler())
                .observeOn(mRxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(disposable -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(
                        imageModels -> {
                            if (imageModels.isEmpty()) {
                                getViewState().showStub();
                            } else {
                                getViewState().showPhotos(imageModels);
                            }
                        },
                        throwable -> {
                            String textError = mResourceManager.getString(R.string.error_load_text);
                            getViewState().showStub();
                            getViewState().showErrorMessage(textError);
                        }));
    }

}
