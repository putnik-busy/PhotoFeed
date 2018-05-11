package com.justapp.photofeed.domain;

import com.justapp.photofeed.domain.repository.DiskRepository;
import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createDiskInfoModel;
import static com.justapp.photofeed.data.generator.DiskResponseModelGenerator.createListImageModel;
import static io.reactivex.Single.just;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sergey Rodionov
 */
@RunWith(MockitoJUnitRunner.class)
public class DiskInteractorTest {

    private static final int LIMIT = 10;
    private static final int OFFSET = 10;

    @Mock
    private DiskRepository mDiskRepository;
    @InjectMocks
    private DiskInteractor mDiskInteractor;

    @Before
    public void setup() {

    }

    @Test
    public void loadDiskInfoShouldPass() throws Exception {
        DiskInfoModel model = createDiskInfoModel();
        when(mDiskRepository.loadDiskInfo()).thenReturn(just(model));

        mDiskInteractor.loadDiskInfo()
                .test()
                .assertNoErrors()
                .assertValue(model::equals);

        verify(mDiskRepository).loadDiskInfo();
    }

    @Test
    public void loadPhotosShouldPass() throws Exception {
        List<ImageModel> model = createListImageModel();
        when(mDiskRepository.loadPhotos(getFiltersParams())).thenReturn(just(model));

        mDiskInteractor.loadPhotos(LIMIT, OFFSET)
                .test()
                .assertNoErrors()
                .assertValue(model::equals);

        verify(mDiskRepository).loadPhotos(getFiltersParams());
    }

    private Map<String, String> getFiltersParams() {
        Map<String, String> params = new HashMap<>();
        params.put("media_type", "image");
        params.put("preview_size", "M");
        params.put("limit", String.valueOf(LIMIT));
        params.put("offset", String.valueOf(OFFSET));
        return params;
    }

}
