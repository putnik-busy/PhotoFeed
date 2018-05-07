package com.justapp.photofeed.data.converter;

import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Rodionov
 */
public class DiskInfoConverterTest {

    private DiskInfoConverter mDiskInfoConverter;

    @Before
    public void setup() {
        mDiskInfoConverter = new DiskInfoConverter();
    }

    @Test
    public void convertDiskInfoResponseToDiskInfoModel() throws Exception {
        DiskInfoModel expected = DiskResponseModelGenerator.createDiskInfoModel();
        DiskInfoResponse response = DiskResponseModelGenerator.createDiskInfoResponse();
        DiskInfoModel actual = mDiskInfoConverter.convert(response);
        assertThat(actual, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void convertDiskInfoResponseToDiskInfoModelExpectedException() throws Exception {
        mDiskInfoConverter.convert(null);
    }

}
