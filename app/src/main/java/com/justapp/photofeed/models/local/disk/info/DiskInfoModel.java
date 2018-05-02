package com.justapp.photofeed.models.local.disk.info;

/**
 * @author Sergey Rodionov
 */
public class DiskInfoModel {

    private int mTrashSize;
    private int mTotalSpace;
    private int mUsedSpace;

    public int getTrashSize() {
        return mTrashSize;
    }

    public void setTrashSize(int trashSize) {
        mTrashSize = trashSize;
    }

    public int getTotalSpace() {
        return mTotalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        mTotalSpace = totalSpace;
    }

    public int getUsedSpace() {
        return mUsedSpace;
    }

    public void setUsedSpace(int usedSpace) {
        mUsedSpace = usedSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiskInfoModel that = (DiskInfoModel) o;

        if (mTrashSize != that.mTrashSize) return false;
        if (mTotalSpace != that.mTotalSpace) return false;
        return mUsedSpace == that.mUsedSpace;
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
        return "DiskInfoModel{" +
                "mTrashSize=" + mTrashSize +
                ", mTotalSpace=" + mTotalSpace +
                ", mUsedSpace=" + mUsedSpace +
                '}';
    }
}
