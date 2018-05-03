package com.justapp.photofeed.di.data;

import android.support.annotation.NonNull;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.mappers.DiskInfoMapper;
import com.justapp.photofeed.data.mappers.ImageListMapper;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.data.repository.DiskRepositoryImpl;
import com.justapp.photofeed.di.scope.DataScope;
import com.justapp.photofeed.domain.repository.DiskRepository;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Rodionov
 */
@Module
public class DataModule {

    @DataScope
    @Provides
    public DiskRepository provideDiskRepository(@NonNull RestApi restApi,
                                                @NonNull DiskInfoMapper diskInfoMapper,
                                                @NonNull ImageListMapper imageListMapper,
                                                @NonNull KeyStoreManager keyStoreManager) {
        return new DiskRepositoryImpl(restApi, diskInfoMapper, imageListMapper, keyStoreManager);
    }

}
