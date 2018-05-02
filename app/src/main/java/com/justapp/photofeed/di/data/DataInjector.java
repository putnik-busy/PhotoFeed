package com.justapp.photofeed.di.data;

import com.justapp.photofeed.di.application.AppInjector;

/**
 * @author Sergey Rodionov
 */
public class DataInjector {

    private static DataComponent sDataComponent;

    public static DataComponent createDataComponent() {
        if (sDataComponent == null) {
            sDataComponent = AppInjector.getAppComponent()
                    .createDataComponent()
                    .build();
        }
        return sDataComponent;
    }

    public static void clearDataModule() {
        sDataComponent = null;
    }

}
