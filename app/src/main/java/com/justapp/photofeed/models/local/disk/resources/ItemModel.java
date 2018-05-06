package com.justapp.photofeed.models.local.disk.resources;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sergey Rodionov
 */
public class ItemModel implements Parcelable {

    private String mName;
    private String mPreview;
    private String mFile;
    private String mCreated;
    private String mModified;
    private String mPath;
    private String mMd5;
    private String mType;
    private String mMimeType;
    private int mSize;

    public ItemModel() {

    }

    public ItemModel(Parcel in) {
        mName = in.readString();
        mPreview = in.readString();
        mFile = in.readString();
        mCreated = in.readString();
        mModified = in.readString();
        mPath = in.readString();
        mMd5 = in.readString();
        mType = in.readString();
        mMimeType = in.readString();
        mSize = in.readInt();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

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

    public String getFile() {
        return mFile;
    }

    public void setFile(String file) {
        mFile = file;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemModel itemModel = (ItemModel) o;

        if (mSize != itemModel.mSize) return false;
        if (mName != null ? !mName.equals(itemModel.mName) : itemModel.mName != null) return false;
        if (mPreview != null ? !mPreview.equals(itemModel.mPreview) : itemModel.mPreview != null)
            return false;
        if (mFile != null ? !mFile.equals(itemModel.mFile) : itemModel.mFile != null)
            return false;
        if (mCreated != null ? !mCreated.equals(itemModel.mCreated) : itemModel.mCreated != null)
            return false;
        if (mModified != null ? !mModified.equals(itemModel.mModified) : itemModel.mModified != null)
            return false;
        if (mPath != null ? !mPath.equals(itemModel.mPath) : itemModel.mPath != null) return false;
        if (mMd5 != null ? !mMd5.equals(itemModel.mMd5) : itemModel.mMd5 != null) return false;
        if (mType != null ? !mType.equals(itemModel.mType) : itemModel.mType != null) return false;
        return mMimeType != null ? mMimeType.equals(itemModel.mMimeType) : itemModel.mMimeType == null;
    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mPreview != null ? mPreview.hashCode() : 0);
        result = 31 * result + (mCreated != null ? mCreated.hashCode() : 0);
        result = 31 * result + (mModified != null ? mModified.hashCode() : 0);
        result = 31 * result + (mPath != null ? mPath.hashCode() : 0);
        result = 31 * result + (mMd5 != null ? mMd5.hashCode() : 0);
        result = 31 * result + (mType != null ? mType.hashCode() : 0);
        result = 31 * result + (mMimeType != null ? mMimeType.hashCode() : 0);
        result = 31 * result + mSize;
        return result;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "mName='" + mName + '\'' +
                ", mPreview='" + mPreview + '\'' +
                ", mCreated='" + mCreated + '\'' +
                ", mModified='" + mModified + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mMd5='" + mMd5 + '\'' +
                ", mType='" + mType + '\'' +
                ", mMimeType='" + mMimeType + '\'' +
                ", mSize=" + mSize +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPreview);
        dest.writeString(mCreated);
        dest.writeString(mModified);
        dest.writeString(mPath);
        dest.writeString(mMd5);
        dest.writeString(mType);
        dest.writeString(mMimeType);
        dest.writeInt(mSize);
    }
}
