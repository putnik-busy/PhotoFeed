package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageListModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author Sergey Rodionov
 */
public class DiskInteractor {

    private final DiskRepository mDiskRepository;
    private final KeyStoreManager mKeyStoreManager;

    @Inject
    public DiskInteractor(@NonNull DiskRepository diskRepository,
                          @NonNull KeyStoreManager keyStoreManager) {
        mDiskRepository = diskRepository;
        mKeyStoreManager = keyStoreManager;
    }

    public Single<DiskInfoModel> loadDiskInfo() {
        return mDiskRepository.loadDiskInfo();
    }

    public Observable<ImageListModel> loadPhotos(int limit, int offset) {
        return mDiskRepository.loadPhotos(getFilterFilesParams(limit, offset));
    }

    public void logout() {
        mDiskRepository.logoutUser();
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
