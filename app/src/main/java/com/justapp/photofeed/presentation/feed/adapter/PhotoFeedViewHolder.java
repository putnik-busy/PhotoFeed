package com.justapp.photofeed.presentation.feed.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.justapp.photofeed.R;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;

/**
 * Холдер для ленты фото
 *
 * @author Sergey Rodionov
 */
public class PhotoFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Picasso mPicasso;
    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private ImageView mImageView;

    /**
     * Констурктор для {@link PhotoFeedViewHolder}
     *
     * @param itemView                 вью элемента списка
     * @param picasso                  фреймворк для загрузки фото
     * @param recyclerViewItemListener листенер событий клика по элементу
     */
    PhotoFeedViewHolder(@NonNull View itemView,
                        @NonNull Picasso picasso,
                        @NonNull RecyclerViewItemListener recyclerViewItemListener) {
        super(itemView);
        mPicasso = picasso;
        mRecyclerViewItemListener = recyclerViewItemListener;
        mImageView = itemView.findViewById(R.id.image_view_photo);
        mImageView.setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        if (mRecyclerViewItemListener != null) {
            mRecyclerViewItemListener.onItemClick(this, getAdapterPosition(), getItemViewType());
        }
    }

    /**
     * Привязывает данные из модели к вьюхам на форме
     *
     * @param imageModel модель, содержащая информация о фото
     */
    void bindView(ImageModel imageModel) {
        Resources resources = mImageView.getResources();
        Drawable placeholderDrawable = getDrawable(resources, R.drawable.ic_photo_black, null);
        Drawable errorDrawable = getDrawable(resources, R.drawable.ic_error_black, null);
        RequestCreator requestCreator = mPicasso.load(imageModel.getPreview());
        requestCreator.fit()
                .centerCrop()
                .placeholder(placeholderDrawable);
        if (errorDrawable != null) {
            requestCreator.error(errorDrawable);
        }
        requestCreator.into(mImageView);
    }
}
