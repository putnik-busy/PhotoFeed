package com.justapp.photofeed.models.local.disk.resources;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class ImageListModel implements Parcelable {

    private List<ImageModel> mImages = new ArrayList<>();

    public ImageListModel() {

    }

    public ImageListModel(Parcel in) {
        mImages = in.createTypedArrayList(ImageModel.CREATOR);
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

    public List<ImageModel> getImages() {
        return mImages;
    }

    public void setImages(List<ImageModel> images) {
        mImages = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageListModel that = (ImageListModel) o;

        return mImages != null ? mImages.equals(that.mImages) : that.mImages == null;
    }

    @Override
    public int hashCode() {
        return mImages != null ? mImages.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ImageListModel{" +
                "mImages=" + mImages +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mImages);
    }
}
