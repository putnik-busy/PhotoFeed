package com.justapp.photofeed.di.data;

import dagger.Subcomponent;

/**
 * @author Sergey Rodionov
 */
@Subcomponent(modules = DataModule.class)
public interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        DataComponent build();
    }

}
