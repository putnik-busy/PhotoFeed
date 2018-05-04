package com.justapp.photofeed.data.mappers;

import android.support.annotation.NonNull;

/**
 * @author Sergey Rodionov
 */
public interface Mapper<S, L> {

    @NonNull
    L convert(S remote);

}
