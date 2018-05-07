
package com.justapp.photofeed.models.remote.disk.info;

import com.google.gson.annotations.SerializedName;

/**
 * Ответ от сервера, содержащий информацию о диске
 */
public class DiskInfoResponse {

    @SerializedName("trash_size")
    private int mTrashSize;
    @SerializedName("total_space")
    private int mTotalSpace;
    @SerializedName("used_space")
    private int mUsedSpace;

    /**
     * @return общий размер файлов в Корзине (байт). Входит в used_space
     */
    public int getTrashSize() {
        return mTrashSize;
    }

    /**
     * Устанавливает общий размер файлов в Корзине (байт). Входит в used_space
     *
     * @param trashSize общий размер файлов в Корзине
     */
    public void setTrashSize(int trashSize) {
        mTrashSize = trashSize;
    }

    /**
     * @return общий объем диска (байт)
     */
    public int getTotalSpace() {
        return mTotalSpace;
    }

    /**
     * Устанавливает общий объем диска (байт)
     *
     * @param totalSpace общий объем диска
     */
    public void setTotalSpace(int totalSpace) {
        mTotalSpace = totalSpace;
    }

    /**
     * @return используемый объем диска (байт)
     */
    public int getUsedSpace() {
        return mUsedSpace;
    }

    /**
     * Устанавливает используемый объем диска (байт)
     *
     * @param usedSpace используемый объем диска
     */
    public void setUsedSpace(int usedSpace) {
        mUsedSpace = usedSpace;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiskInfoResponse response = (DiskInfoResponse) o;

        if (mTrashSize != response.mTrashSize) {
            return false;
        }
        if (mTotalSpace != response.mTotalSpace) {
            return false;
        }
        return mUsedSpace == response.mUsedSpace;
    }

    @Override
    public int hashCode() {
        int result = mTrashSize;
        result = 31 * result + mTotalSpace;
        result = 31 * result + mUsedSpace;
        return result;
    }

    @Override
    public String toString() {
        return "DiskInfoResponse{" +
                "mTrashSize=" + mTrashSize +
                ", mTotalSpace=" + mTotalSpace +
                ", mUsedSpace=" + mUsedSpace +
                '}';
    }

}
