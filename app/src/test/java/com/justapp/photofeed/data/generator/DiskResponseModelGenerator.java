package com.justapp.photofeed.data.generator;

import com.justapp.photofeed.models.local.disk.info.DiskInfoModel;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.models.remote.disk.info.DiskInfoResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageListResponse;
import com.justapp.photofeed.models.remote.disk.resources.ImageResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Генератор моделей ответов с Диска
 *
 * @author Sergey Rodionov
 */
public final class DiskResponseModelGenerator {

    //Информация о диске
    private static final int USED_SPACE = 100;
    private static final int TRASH_SIZE = 200;
    private static final int TOTAL_SPACE = 300;

    //Список фото пользователя
    private static final String CREATED_INFO = "12.12.2012";
    private static final String FILE_NAME = "testName";
    private static final String MD5 = "67452301h";
    private static final String MIME_TYPE = "image/jpeg";
    private static final String MODIFIED = "2016-07-12T10:30:29+00:00";
    private static final String NAME = "IMG_123.JPG";
    private static final String PATH = "disk:/test/IMG_123.JPG";
    private static final String PREVIEW = "https://test.ru";
    private static final int IMAGE_SIZE = 123;
    private static final String TYPE = "file";
    private static final int LIMIT = 10;

    public static DiskInfoModel createDiskInfoModel() {
        DiskInfoModel model = new DiskInfoModel();
        model.setUsedSpace(USED_SPACE);
        model.setTrashSize(TRASH_SIZE);
        model.setTotalSpace(TOTAL_SPACE);
        return model;
    }

    public static DiskInfoResponse createDiskInfoResponse() {
        DiskInfoResponse response = new DiskInfoResponse();
        response.setUsedSpace(USED_SPACE);
        response.setTrashSize(TRASH_SIZE);
        response.setTotalSpace(TOTAL_SPACE);
        return response;
    }

    public static ImageModel createImageModel() {
        ImageModel model = new ImageModel();
        model.setCreated(CREATED_INFO);
        model.setFile(FILE_NAME);
        model.setMd5(MD5);
        model.setMimeType(MIME_TYPE);
        model.setModified(MODIFIED);
        model.setName(NAME);
        model.setPath(PATH);
        model.setPreview(PREVIEW);
        model.setSize(IMAGE_SIZE);
        model.setType(TYPE);
        return model;
    }

    public static List<ImageModel> createListImageModel() {
        List<ImageModel> list = new ArrayList<>();
        list.add(createImageModel());
        return list;
    }

    public static ImageResponse createImageResponse() {
        ImageResponse response = new ImageResponse();
        response.setCreated(CREATED_INFO);
        response.setFile(FILE_NAME);
        response.setMd5(MD5);
        response.setMimeType(MIME_TYPE);
        response.setModified(MODIFIED);
        response.setName(NAME);
        response.setPath(PATH);
        response.setPreview(PREVIEW);
        response.setSize(IMAGE_SIZE);
        response.setType(TYPE);
        return response;
    }

    public static ImageListResponse createImageListResponse() {
        ImageListResponse response = new ImageListResponse();
        response.setItems(createListImageResponse());
        response.setLimit(LIMIT);
        response.setOffset(LIMIT);
        return response;
    }

    public static List<ImageResponse> createListImageResponse() {
        List<ImageResponse> list = new ArrayList<>();
        list.add(createImageResponse());
        return list;
    }

}
