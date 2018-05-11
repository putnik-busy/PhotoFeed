package com.justapp.photofeed.di.data;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.converter.DiskInfoConverter;
import com.justapp.photofeed.data.converter.ImageConverter;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.data.repository.DiskRepositoryImpl;
import com.justapp.photofeed.di.scope.Data;
import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.routers.photoview.PhotoViewRouter;
import com.justapp.photofeed.routers.photoview.PhotoViewRouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль {@link Module} для работы после авторизции в приложении.
 * <p>
 * Провайдит зависимости, необходимые после авторизации
 *
 * @author Sergey Rodionov
 */
@Module
public class DataModule {

    @Data
    @Provides
    DiskRepository provideDiskRepository(@NonNull RestApi restApi,
                                         @NonNull DiskInfoConverter diskInfoConverter,
                                         @NonNull ImageConverter imageConverter) {
        return new DiskRepositoryImpl(restApi, diskInfoConverter, imageConverter);
    }

    @Data
    @Provides
    PhotoViewRouter providePhotoViewRouter() {
        return new PhotoViewRouterImpl();
    }

}
