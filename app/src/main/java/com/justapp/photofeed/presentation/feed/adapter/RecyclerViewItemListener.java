package com.justapp.photofeed.presentation.feed.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * @author Sergey Rodionov
 */
public interface RecyclerViewItemListener {

    void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType);
}
