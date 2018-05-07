package com.justapp.photofeed.data.repository;

import com.justapp.photofeed.data.converter.DiskInfoConverter;
import com.justapp.photofeed.data.converter.ImageConverter;
import com.justapp.photofeed.data.network.RestApi;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createDiskInfoModel;
import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createDiskInfoResponse;
import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createImageListResponse;
import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createListImageModel;
import static io.reactivex.Single.just;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sergey Rodionov
 */
@RunWith(MockitoJUnitRunner.class)
public class DiskRepositoryImplTest {

    @Mock
    private RestApi mRestApi;
    @Spy
    private DiskInfoConverter mDiskInfoConverter = new DiskInfoConverter();
    @Spy
    private ImageConverter mImageConverter = new ImageConverter();
    @InjectMocks
    private DiskRepositoryImpl mDiskRepository;
    private DiskInfoResponse mDiskInfoResponse;
    private ImageListResponse mImageListResponse;

    @Before
    public void setup() {
        mDiskInfoResponse = createDiskInfoResponse();
        mImageListResponse = createImageListResponse();
        when(mRestApi.getDiskInfo()).thenReturn(just(mDiskInfoResponse));
        when(mRestApi.getPhotos(Collections.emptyMap())).thenReturn(just(mImageListResponse));
    }

    @Test
    public void loadDiskInfoShouldPass() throws Exception {
        DiskInfoModel model = createDiskInfoModel();

        mDiskRepository.loadDiskInfo()
                .test()
                .assertNoErrors()
                .assertValue(model::equals);

        verify(mDiskInfoConverter).convert(mDiskInfoResponse);
    }

    @Test
    public void loadPhotosShouldPass() throws Exception {
        List<ImageModel> models = createListImageModel();

        mDiskRepository.loadPhotos(Collections.emptyMap())
                .test()
                .assertNoErrors()
                .assertValue(models::equals);

        verify(mImageConverter).convertList(mImageListResponse.getItems());
    }

}
