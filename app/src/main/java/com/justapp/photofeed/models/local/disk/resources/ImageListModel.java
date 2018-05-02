package com.justapp.photofeed.models.local.disk.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class ImageListModel {

    private List<ItemModel> mItems = new ArrayList<>();


    public List<ItemModel> getItems() {
        return mItems;
    }

    public void setItems(List<ItemModel> items) {
        mItems = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageListModel that = (ImageListModel) o;

        return mItems != null ? mItems.equals(that.mItems) : that.mItems == null;
    }

    @Override
    public int hashCode() {
        return mItems != null ? mItems.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ImageListModel{" +
                "mItems=" + mItems +
                '}';
    }
}
