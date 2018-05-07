
package com.justapp.photofeed.models.remote.disk.resources;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ от сервера, содержащий список фото пользователя
 */
public class ImageListResponse {

    @SerializedName("items")
    private List<ImageResponse> mItems = new ArrayList<>();
    @SerializedName("limit")
    private int mLimit;
    @SerializedName("offset")
    private int mOffset;

    /**
     * @return элементы списка
     */
    public List<ImageResponse> getItems() {
        return mItems;
    }

    /**
     * Устанавливает элементы списка
     *
     * @param items элементы списка
     */
    public void setItems(List<ImageResponse> items) {
        mItems = items;
    }

    /**
     * @return количество элементов на странице
     */
    public int getLimit() {
        return mLimit;
    }

    /**
     * Устанавливает количество элементов на странице
     *
     * @param limit Количество элементов на странице
     */
    public void setLimit(int limit) {
        mLimit = limit;
    }

    /**
     * @return смещение от начала списка
     */
    public int getOffset() {
        return mOffset;
    }

    /**
     * Устаналивает смещение от начала списка
     *
     * @param offset смещение от начала списка
     */
    public void setOffset(int offset) {
        mOffset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageListResponse that = (ImageListResponse) o;

        if (mLimit != that.mLimit) {
            return false;
        }
        if (mOffset != that.mOffset) {
            return false;
        }
        return mItems != null ? mItems.equals(that.mItems) : that.mItems == null;
    }

    @Override
    public int hashCode() {
        int result = mItems != null ? mItems.hashCode() : 0;
        result = 31 * result + mLimit;
        result = 31 * result + mOffset;
        return result;
    }

    @Override
    public String toString() {
        return "ImageListResponse{" +
                "mItems=" + mItems +
                ", mLimit=" + mLimit +
                ", mOffset=" + mOffset +
                '}';
    }

}
