package com.justapp.photofeed.di.data;

import com.justapp.photofeed.di.scope.DataScope;
import com.justapp.photofeed.presentation.feed.activity.FeedActivity;
import com.justapp.photofeed.presentation.feed.fragment.FeedFragment;
import com.justapp.photofeed.presentation.photoview.fragment.PhotoViewFragment;

import dagger.Subcomponent;

/**
 * @author Sergey Rodionov
 */
@DataScope
@Subcomponent(modules = DataModule.class)
public interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        DataComponent build();
    }

    void inject(FeedActivity activity);

    void inject(FeedFragment fragment);

    void inject(PhotoViewFragment fragment);

}
