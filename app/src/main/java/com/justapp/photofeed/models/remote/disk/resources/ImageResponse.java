
package com.justapp.photofeed.models.remote.disk.resources;

import com.google.gson.annotations.SerializedName;

/**
 * Ответ от сервера, содержащий подробную информацию о фото пользователя
 */
public class ImageResponse {

    @SerializedName("name")
    private String mName;
    @SerializedName("preview")
    private String mPreview;
    @SerializedName("file")
    private String mFile;
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

    /**
     * @return имя
     */
    public String getName() {
        return mName;
    }

    /**
     * Устаналивает имя
     *
     * @param name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * @return URL превью файла
     */
    public String getPreview() {
        return mPreview;
    }

    /**
     * Устаналивает URL превью файла
     *
     * @param preview URL превью файла
     */
    public void setPreview(String preview) {
        mPreview = preview;
    }

    /**
     * @return URL для скачивания файла
     */
    public String getFile() {
        return mFile;
    }

    /**
     * Устаналивает URL для скачивания файла
     *
     * @param file URL для скачивания файла
     */
    public void setFile(String file) {
        mFile = file;
    }

    /**
     * @return дата создания
     */
    public String getCreated() {
        return mCreated;
    }

    /**
     * Устанавливает дату создания
     *
     * @param created дата создания
     */
    public void setCreated(String created) {
        mCreated = created;
    }

    /**
     * @return дата изменения
     */
    public String getModified() {
        return mModified;
    }

    /**
     * Устаналивает дату изменения
     *
     * @param modified дата изменения
     */
    public void setModified(String modified) {
        mModified = modified;
    }

    /**
     * @return путь к ресурсу
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Устаналивает  путь к ресурсу
     *
     * @param path путь к ресурсу
     */
    public void setPath(String path) {
        mPath = path;
    }

    /**
     * @return MD5-хэш
     */
    public String getMd5() {
        return mMd5;
    }

    /**
     * Устаналивает MD5-хэш
     *
     * @param md5 MD5-хэш
     */
    public void setMd5(String md5) {
        mMd5 = md5;
    }

    /**
     * @return тип
     */
    public String getType() {
        return mType;
    }

    /**
     * Устанавливает тип
     *
     * @param type тип
     */
    public void setType(String type) {
        mType = type;
    }

    /**
     * @return MIME-тип файла
     */
    public String getMimeType() {
        return mMimeType;
    }

    /**
     * Устанавливает MIME-тип файла
     *
     * @param mimeType MIME-тип файла
     */
    public void setMimeType(String mimeType) {
        mMimeType = mimeType;
    }

    /**
     * @return размер файла
     */
    public int getSize() {
        return mSize;
    }

    /**
     * Устанавливает размер файла
     *
     * @param size размер файла
     */
    public void setSize(int size) {
        mSize = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageResponse that = (ImageResponse) o;

        if (mSize != that.mSize) {
            return false;
        }
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) {
            return false;
        }
        if (mPreview != null ? !mPreview.equals(that.mPreview) : that.mPreview != null) {
            return false;
        }
        if (mFile != null ? !mFile.equals(that.mFile) : that.mFile != null) {
            return false;
        }
        if (mCreated != null ? !mCreated.equals(that.mCreated) : that.mCreated != null) {
            return false;
        }
        if (mModified != null ? !mModified.equals(that.mModified) : that.mModified != null) {
            return false;
        }
        if (mPath != null ? !mPath.equals(that.mPath) : that.mPath != null) {
            return false;
        }
        if (mMd5 != null ? !mMd5.equals(that.mMd5) : that.mMd5 != null) {
            return false;
        }
        if (mType != null ? !mType.equals(that.mType) : that.mType != null) {
            return false;
        }
        return mMimeType != null ? mMimeType.equals(that.mMimeType) : that.mMimeType == null;
    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mPreview != null ? mPreview.hashCode() : 0);
        result = 31 * result + (mFile != null ? mFile.hashCode() : 0);
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
        return "ImageResponse{" +
                "mName='" + mName + '\'' +
                ", mPreview='" + mPreview + '\'' +
                ", mFile='" + mFile + '\'' +
                ", mCreated='" + mCreated + '\'' +
                ", mModified='" + mModified + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mMd5='" + mMd5 + '\'' +
                ", mType='" + mType + '\'' +
                ", mMimeType='" + mMimeType + '\'' +
                ", mSize=" + mSize +
                '}';
    }

}
