package com.justapp.photofeed.data.converter;

import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.models.remote.disk.resources.ImageResponse;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Rodionov
 */
public class ImageConverterTest {

    private ImageConverter mImageConverter;

    @Before
    public void setup() {
        mImageConverter = new ImageConverter();
    }

    @Test
    public void convertImageResponseToImageModel() throws Exception {
        ImageModel expected = DiskResponseModelGenerator.createImageModel();
        ImageResponse response = DiskResponseModelGenerator.createImageResponse();
        ImageModel actual = mImageConverter.convert(response);
        assertThat(actual, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void convertImageResponseToImageModelExpectedException() throws Exception {
        mImageConverter.convert(null);
    }

}
