
package com.justapp.photofeed.models.remote.disk.resources;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    private String mName;
    @SerializedName("preview")
    private String mPreview;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("modified")
    private String mModified;
    @SerializedName("path")
    private String mPath;
    @SerializedName("md5")
    private String mMd5;
    @SerializedName("type")
    private String mType;
    @SerializedName("mime_type")
    private String mMimeType;
    @SerializedName("size")
    private int mSize;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPreview() {
        return mPreview;
    }

    public void setPreview(String preview) {
        mPreview = preview;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getMd5() {
        return mMd5;
    }

    public void setMd5(String md5) {
        mMd5 = md5;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public void setMimeType(String mimeType) {
        mMimeType = mimeType;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }
}
