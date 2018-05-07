package com.justapp.photofeed.data.converter;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.base.AbstractOneWayConverter;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.models.remote.disk.resources.ImageResponse;

import java.util.Map;

import javax.inject.Inject;

/**
 * Реализация {@link AbstractOneWayConverter} для запроса {@link RestApi#getPhotos(Map)} ()}
 *
 * @author Sergey Rodionov
 */
public final class ImageConverter extends AbstractOneWayConverter<ImageResponse, ImageModel> {

    @Inject
    public ImageConverter() {
        //необходим для dagger
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public ImageModel convert(@NonNull ImageResponse remote) {
        ImageModel imageModel = new ImageModel();
        imageModel.setCreated(remote.getCreated());
        imageModel.setMd5(remote.getMd5());
        imageModel.setMimeType(remote.getMimeType());
        imageModel.setModified(remote.getModified());
        imageModel.setName(remote.getName());
        imageModel.setPath(remote.getPath());
        imageModel.setPreview(remote.getPreview());
        imageModel.setFile(remote.getFile());
        imageModel.setType(remote.getType());
        imageModel.setSize(remote.getSize());
        return imageModel;
    }

}
