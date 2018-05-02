package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.models.remote.disk.resources.Item;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
public class ItemMapper implements Mapper<Item, ItemModel> {

    @Inject
    public ItemMapper() {
        //необходим для dagger
    }

    @NonNull
    @Override
    public ItemModel convert(Item remote) {
        ItemModel itemModel = new ItemModel();
        itemModel.setCreated(remote.getCreated());
        itemModel.setMd5(remote.getMd5());
        itemModel.setMimeType(remote.getMimeType());
        itemModel.setModified(remote.getModified());
        itemModel.setName(remote.getName());
        itemModel.setPath(remote.getPath());
        itemModel.setPreview(remote.getPreview());
        itemModel.setType(remote.getType());
        itemModel.setSize(remote.getSize());
        return itemModel;
    }

}
