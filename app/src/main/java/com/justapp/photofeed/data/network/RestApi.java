package com.justapp.photofeed.data.network;

import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Интерфейс API для работы с Диском
 *
 * @author Sergey Rodionov
 */
public interface RestApi {

    /**
     * @return возвращает инофрмацию о диске
     */
    @GET("v1/disk")
    Single<DiskInfoResponse> getDiskInfo();

    /**
     * @param map параметры запроса, например размер, тип фото и прочее
     * @return возвращает список фото пользователя
     */
    @GET("v1/disk/resources/files")
    Single<ImageListResponse> getPhotos(@QueryMap Map<String, String> map);
}
