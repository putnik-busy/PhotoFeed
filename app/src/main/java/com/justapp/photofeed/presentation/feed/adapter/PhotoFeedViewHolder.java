package com.justapp.photofeed.presentation.feed.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.justapp.photofeed.R;
import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.squareup.picasso.Picasso;

/**
 * @author Sergey Rodionov
 */
public class PhotoFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Picasso mPicasso;
    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private ImageView mImageView;

    public PhotoFeedViewHolder(@NonNull View itemView,
                               @NonNull Picasso picasso,
                               @NonNull RecyclerViewItemListener recyclerViewItemListener) {
        super(itemView);
        mPicasso = picasso;
        mRecyclerViewItemListener = recyclerViewItemListener;
        mImageView = itemView.findViewById(R.id.item_photo);
    }

    @Override
    public void onClick(View v) {
        if (mRecyclerViewItemListener != null) {
            mRecyclerViewItemListener.onItemClick(this, getAdapterPosition(), getItemViewType());
        }
    }

    public void bindView(ItemModel itemModel) {
        mPicasso.load(itemModel.getPreview())
                .placeholder(R.drawable.ic_photo_black)
                .error(R.drawable.ic_error_black)
                .into(mImageView);
    }
}
