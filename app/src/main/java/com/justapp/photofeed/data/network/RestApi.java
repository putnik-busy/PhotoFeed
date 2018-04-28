package com.justapp.photofeed.data.network;

import android.support.annotation.Nullable;

import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import java.util.Map;

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
    Single<ImageListResponse> getFiles(@QueryMap Map<String, String> map);
}
