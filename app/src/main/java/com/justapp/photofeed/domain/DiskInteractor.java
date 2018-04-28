package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;

import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageListModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Rodionov
 */
public class DiskInteractor {

    private final DiskRepository mDiskRepository;

    @Inject
    public DiskInteractor(@NonNull DiskRepository diskRepository) {
        mDiskRepository = diskRepository;
    }

    public Single<DiskInfoModel> getDiskInfo() {
        return mDiskRepository.getDiskInfo();
    }

    public Single<ImageListModel> getFiles(int limit, int offset) {
        return mDiskRepository.getFiles(getFilterFilesParams(limit, offset));
    }

    private Map<String, String> getFilterFilesParams(int limit, int offset) {
        Map<String, String> params = new HashMap<>();
        params.put("media_type", "image");
        params.put("preview_size", "M");
        params.put("limit", String.valueOf(limit));
        params.put("offset", String.valueOf(offset));
        return params;
    }

}
