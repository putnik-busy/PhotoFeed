package com.justapp.photofeed.models.local.disk.resources;

import com.justapp.photofeed.models.remote.disk.resources.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class ImageListModel {

    private List<Item> mItems = new ArrayList<>();


    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }
}
