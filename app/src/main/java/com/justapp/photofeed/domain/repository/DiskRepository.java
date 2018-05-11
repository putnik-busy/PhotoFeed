package com.justapp.photofeed.domain.repository;

import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Репозиторий, предоставляющий операции над фото пользователя и получения информации о диске
 *
 * @author Sergey Rodionov
 */
public interface DiskRepository {

    /**
     * Загружает информацию о диске
     *
     * @return информация о диске
     */
    Single<DiskInfoModel> loadDiskInfo();

    /**
     * Загружает список фото пользователя со смещением offset
     *
     * @param map контейнер с фильтрами
     * @return список фото пользователя
     */
    Single<List<ImageModel>> loadPhotos(Map<String, String> map);
}
