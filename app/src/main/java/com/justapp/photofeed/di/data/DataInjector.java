package com.justapp.photofeed.di.data;

import com.justapp.photofeed.di.application.AppInjector;

/**
 * Инжектор для компонента {@link DataComponent}
 *
 * @author Sergey Rodionov
 */
public class DataInjector {

    private DataInjector() {
        throw new UnsupportedOperationException("Не надо создавать экземпляр класса");
    }

    private static DataComponent sDataComponent;

    /**
     * Инициализация {@link DataComponent} компонента
     *
     * @return {@link DataComponent}
     */
    public static DataComponent createDataComponent() {
        if (sDataComponent == null) {
            sDataComponent = AppInjector.getAppComponent()
                    .createDataComponent()
                    .build();
        }
        return sDataComponent;
    }

    /**
     * Очищает {@link DataComponent}
     */
    public static void clearDataModule() {
        sDataComponent = null;
    }

}
