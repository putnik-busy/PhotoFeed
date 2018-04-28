package com.justapp.photofeed.data.repository;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.mappers.DiskInfoMapper;
import com.justapp.photofeed.data.mappers.ImageListMapper;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageListModel;

import java.util.Map;

import io.reactivex.Single;

/**
 * @author Sergey Rodionov
 */
public class DiskRepositoryImpl implements DiskRepository {

    private final RestApi mRestApi;
    private final DiskInfoMapper mDiskInfoMapper;
    private final ImageListMapper mImageListMapper;

    public DiskRepositoryImpl(@NonNull RestApi restApi,
                              @NonNull DiskInfoMapper diskInfoMapper,
                              @NonNull ImageListMapper imageListMapper) {
        mRestApi = restApi;
        mDiskInfoMapper = diskInfoMapper;
        mImageListMapper = imageListMapper;
    }

    @Override
    public Single<DiskInfoModel> getDiskInfo() {
        return mRestApi.getDiskInfo()
                .map(mDiskInfoMapper::convert);
    }

    @Override
    public Single<ImageListModel> getFiles(Map<String, String> map) {
        return mRestApi.getFiles(map)
                .map(mImageListMapper::convert);
    }

}
