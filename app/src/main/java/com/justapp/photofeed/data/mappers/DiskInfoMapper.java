package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * @author Sergey Rodionov
 */
public class DiskInfoMapper implements Mapper<DiskInfoResponse, DiskInfoModel> {

    @Inject
    public DiskInfoMapper() {
        //необходим для dagger
    }

    @NonNull
    @Override
    public DiskInfoModel convert(@NonNull DiskInfoResponse remote) {
        Preconditions.checkNotNull(remote);
        DiskInfoModel diskInfoModel = new DiskInfoModel();
        diskInfoModel.setTotalSpace(remote.getTotalSpace());
        diskInfoModel.setTrashSize(remote.getTrashSize());
        diskInfoModel.setUsedSpace(remote.getUsedSpace());
        return diskInfoModel;
    }

}
