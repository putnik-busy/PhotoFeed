package com.justapp.photofeed.domain.repository;

import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageListModel;

import java.util.Map;

import io.reactivex.Single;

/**
 * @author Sergey Rodionov
 */
public interface DiskRepository {

    Single<DiskInfoModel> loadDiskInfo();

    Single<ImageListModel> loadPhotos(Map<String, String> map);

    void logoutUser();
}
