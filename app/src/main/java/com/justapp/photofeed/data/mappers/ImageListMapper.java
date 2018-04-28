package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

import com.justapp.photofeed.models.local.disk.resources.ImageListModel;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * @author Sergey Rodionov
 */
public class ImageListMapper implements Mapper<ImageListResponse, ImageListModel> {

    @Inject
    public ImageListMapper() {
        //необходим для dagger
    }

    @NonNull
    @Override
    public ImageListModel convert(ImageListResponse remote) {
        Preconditions.checkNotNull(remote);
        ImageListModel imageListModel = new ImageListModel();
        imageListModel.setItems(remote.getItems());
        return imageListModel;
    }
}
