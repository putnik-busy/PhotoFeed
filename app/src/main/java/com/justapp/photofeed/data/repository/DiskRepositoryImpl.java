package com.justapp.photofeed.data.repository;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.converter.DiskInfoConverter;
import com.justapp.photofeed.data.converter.ImageConverter;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Реализация {@link DiskRepository}
 *
 * @author Sergey Rodionov
 */
public class DiskRepositoryImpl implements DiskRepository {

    private final RestApi mRestApi;
    private final DiskInfoConverter mDiskInfoConverter;
    private final ImageConverter mImageConverter;

    /**
     * Конструктор для {@link DiskRepositoryImpl}
     *
     * @param restApi           API для работы с Диском
     * @param diskInfoConverter конвертор запроса информации о диске в локальную модель
     * @param imageConverter    конвертор запроса информации о списке фото в локальную модель
     */
    public DiskRepositoryImpl(@NonNull RestApi restApi,
                              @NonNull DiskInfoConverter diskInfoConverter,
                              @NonNull ImageConverter imageConverter) {
        mRestApi = restApi;
        mDiskInfoConverter = diskInfoConverter;
        mImageConverter = imageConverter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<DiskInfoModel> loadDiskInfo() {
        return mRestApi.getDiskInfo()
                .map(mDiskInfoConverter::convert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<List<ImageModel>> loadPhotos(Map<String, String> map) {
        return mRestApi.getPhotos(map)
                .map((ImageListResponse fromList) -> mImageConverter.convertList(fromList.getItems()));
    }

}
