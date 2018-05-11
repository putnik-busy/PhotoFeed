package com.justapp.photofeed.domain;

import android.support.annotation.NonNull;

import com.justapp.photofeed.di.scope.Data;
import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Интерактор, предоставляющий операции над фото пользователя и получения информации о диске
 *
 * @author Sergey Rodionov
 */
@Data
public class DiskInteractor {

    private final DiskRepository mDiskRepository;

    /**
     * Конструктор для {@link DiskInteractor}
     *
     * @param diskRepository репозиторий, для работы с запросами пользователя
     */
    @Inject
    DiskInteractor(@NonNull DiskRepository diskRepository) {
        mDiskRepository = diskRepository;
    }

    /**
     * Загружает информацию о диске(размер, занятая память и т.д.)
     *
     * @return информацию о диске(размер, занятая память и т.д.)
     */
    Single<DiskInfoModel> loadDiskInfo() {
        return mDiskRepository.loadDiskInfo();
    }

    /**
     * Загружает список фото пользователя со смещением offset
     *
     * @param limit  количество выводимых вложенных ресурсов
     * @param offset смещение от начала списка вложенных ресурсов
     * @return список фото пользователя
     */
    public Single<List<ImageModel>> loadPhotos(int limit, int offset) {
        return mDiskRepository.loadPhotos(getFilterFilesParams(limit, offset));
    }

    /**
     * Создает контейнер с фильтрами для загрузки фото пользователя
     * <p>
     * Основными параметрами являются:
     * media_type - фильтр по медиа типу, например image
     * preview_size - размер превью, поддерживает значения от XXXS до XXXL
     *
     * @param limit  количество выводимых вложенных ресурсов
     * @param offset смещение от начала списка вложенных ресурсов
     * @return контейнер с фильтрами для загрузки фото
     */
    private Map<String, String> getFilterFilesParams(int limit, int offset) {
        Map<String, String> params = new HashMap<>();
        params.put("media_type", "image");
        params.put("preview_size", "M");
        params.put("limit", String.valueOf(limit));
        params.put("offset", String.valueOf(offset));
        return params;
    }

}
