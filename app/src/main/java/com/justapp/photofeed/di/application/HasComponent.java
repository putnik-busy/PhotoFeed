package com.justapp.photofeed.di.application;

/**
 * Класс, реализующий этот интерфейс, возвращает Dagger компонент
 *
 * @param <T> класс компонента
 * @author Sergey Rodionov
 */
public interface HasComponent<T> {

    /**
     * @return возвращает запрашиваемый компонент
     */
    T getComponent();
}
