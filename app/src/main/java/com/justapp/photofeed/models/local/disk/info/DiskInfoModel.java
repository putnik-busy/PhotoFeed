package com.justapp.photofeed.models.local.disk.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Локальная модель ответа от сервера, содержащая информацию о диске
 *
 * @author Sergey Rodionov
 */
public class DiskInfoModel implements Parcelable {

    private int mTrashSize;
    private int mTotalSpace;
    private int mUsedSpace;

    public DiskInfoModel() {
        //необходим для Parcelable
    }

    protected DiskInfoModel(Parcel in) {
        mTrashSize = in.readInt();
        mTotalSpace = in.readInt();
        mUsedSpace = in.readInt();
    }

    public static final Creator<DiskInfoModel> CREATOR = new Creator<DiskInfoModel>() {
        @Override
        public DiskInfoModel createFromParcel(Parcel in) {
            return new DiskInfoModel(in);
        }

        @Override
        public DiskInfoModel[] newArray(int size) {
            return new DiskInfoModel[size];
        }
    };

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

        DiskInfoModel that = (DiskInfoModel) o;

        if (mTrashSize != that.mTrashSize) {
            return false;
        }
        if (mTotalSpace != that.mTotalSpace) {
            return false;
        }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTrashSize);
        dest.writeInt(mTotalSpace);
        dest.writeInt(mUsedSpace);
    }

}
