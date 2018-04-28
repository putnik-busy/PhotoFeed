
package com.justapp.photofeed.models.remote.disk.info;

import com.google.gson.annotations.SerializedName;

public class SystemFolders {

    @SerializedName("applications")
    private String mApplications;
    @SerializedName("downloads")
    private String mDownloads;

    public String getApplications() {
        return mApplications;
    }

    public void setApplications(String applications) {
        mApplications = applications;
    }

    public String getDownloads() {
        return mDownloads;
    }

    public void setDownloads(String downloads) {
        mDownloads = downloads;
    }
}
