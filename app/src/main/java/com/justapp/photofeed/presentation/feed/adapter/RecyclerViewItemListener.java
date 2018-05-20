package com.justapp.photofeed.presentation.feed.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Слушатель событий  для {@link RecyclerView}
 *
 * @author Sergey Rodionov
 */
public interface RecyclerViewItemListener {

    /**
     * Слушатель клика по item
     *
     * @param sender          вьюходер
     * @param adapterPosition позиция клика
     * @param viewType        тип вью
     */
    void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType);
}
