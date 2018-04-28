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
}
