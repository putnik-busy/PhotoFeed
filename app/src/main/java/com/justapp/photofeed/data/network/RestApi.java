package com.justapp.photofeed.data.network;

import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Sergey Rodionov
 */
public interface RestApi {

    @GET("v1/disk")
    Single<DiskInfoResponse> getDiskInfo();

    @GET("v1/disk/resources/files")
    Observable<ImageListResponse> getPhotos(@QueryMap Map<String, String> map);
}
