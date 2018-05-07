package com.justapp.photofeed.data.converter;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.base.OneWayConverter;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * Реализация {@link OneWayConverter} для запроса {@link RestApi#getDiskInfo()}
 *
 * @author Sergey Rodionov
 */
public class DiskInfoConverter implements OneWayConverter<DiskInfoResponse, DiskInfoModel> {

    @Inject
    public DiskInfoConverter() {
        //необходим для dagger
    }

    /**
     * {@inheritDoc}
     */
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
