
package com.justapp.photofeed.models.remote.disk.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiskInfoResponse {

    @SerializedName("trash_size")
    private int mTrashSize;
    @SerializedName("total_space")
    private int mTotalSpace;
    @SerializedName("used_space")
    private int mUsedSpace;
    @SerializedName("system_folders")
    @Expose(deserialize = false)
    private SystemFolders mSystemFolders;

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

    public SystemFolders getSystemFolders() {
        return mSystemFolders;
    }

    public void setSystemFolders(SystemFolders systemFolders) {
        mSystemFolders = systemFolders;
    }
}
