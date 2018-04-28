
package com.justapp.photofeed.models.remote.disk.resources;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImageListResponse {

    @SerializedName("items")
    private List<Item> mItems = new ArrayList<>();
    @SerializedName("limit")
    private int mLimit;
    @SerializedName("offset")
    private int mOffset;

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }


}
