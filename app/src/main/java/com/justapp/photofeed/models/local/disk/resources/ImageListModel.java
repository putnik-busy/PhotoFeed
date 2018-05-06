package com.justapp.photofeed.models.local.disk.resources;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class ImageListModel implements Parcelable {

    private List<ItemModel> mItems = new ArrayList<>();

    public ImageListModel() {

    }

    public ImageListModel(Parcel in) {
        mItems = in.createTypedArrayList(ItemModel.CREATOR);
    }

    public static final Creator<ImageListModel> CREATOR = new Creator<ImageListModel>() {
        @Override
        public ImageListModel createFromParcel(Parcel in) {
            return new ImageListModel(in);
        }

        @Override
        public ImageListModel[] newArray(int size) {
            return new ImageListModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mItems);
    }
}
