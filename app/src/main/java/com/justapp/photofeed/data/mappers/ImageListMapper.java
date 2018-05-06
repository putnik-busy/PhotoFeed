package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

import com.justapp.photofeed.models.local.disk.resources.ImageListModel;
import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * @author Sergey Rodionov
 */
public class ImageListMapper implements Mapper<ImageListResponse, ImageListModel> {

    private final ItemMapper mItemMapper;

    @Inject
    public ImageListMapper(ItemMapper itemMapper) {
        mItemMapper = itemMapper;
    }

    @NonNull
    @Override
    public ImageListModel convert(ImageListResponse remote) {
        Preconditions.checkNotNull(remote);
        ImageListModel imageListModel = new ImageListModel();
        List<ItemModel> itemModels = new ArrayList<>();
        for (ImageResponse item : remote.getItems()) {
            ItemModel itemModel = mItemMapper.convert(item);
            itemModels.add(itemModel);
        }
        imageListModel.setItems(itemModels);
        return imageListModel;
    }
}
