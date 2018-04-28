package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public interface Mapper<R, L> {

    @NonNull
    L convert(R remote);

}
