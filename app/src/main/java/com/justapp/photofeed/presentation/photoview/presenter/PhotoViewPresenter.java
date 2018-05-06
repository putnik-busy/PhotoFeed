package com.justapp.photofeed.presentation.photoview.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.justapp.photofeed.presentation.base.BasePresenter;
import com.justapp.photofeed.presentation.photoview.view.SinglePhotoView;

/**
 * @author Sergey Rodionov
 */
@InjectViewState
public class PhotoViewPresenter extends BasePresenter<SinglePhotoView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showPhoto();
    }

}
