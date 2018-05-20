package com.justapp.photofeed.presentation.feed.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justapp.photofeed.R;
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер для ленты фото
 *
 * @author Sergey Rodionov
 */
public class PhotoFeedAdapter extends RecyclerView.Adapter<PhotoFeedViewHolder> {

    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private final Picasso mPicasso;
    private final List<ImageModel> mImageModels;

    /**
     * Констурктор для {@link PhotoFeedAdapter}
     *
     * @param recyclerViewItemListener листенер событий клика по элементу
     * @param picasso                  фреймворк для загрузки фото
     */
    public PhotoFeedAdapter(@NonNull RecyclerViewItemListener recyclerViewItemListener,
                            @NonNull Picasso picasso) {
        mRecyclerViewItemListener = recyclerViewItemListener;
        mPicasso = picasso;
        mImageModels = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public PhotoFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feed_photo_item, parent, false);
        return new PhotoFeedViewHolder(view, mPicasso, mRecyclerViewItemListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull PhotoFeedViewHolder holder, int position) {
        holder.bindView(mImageModels.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mImageModels.size();
    }

    /**
     * Добавляет фото для отображения
     *
     * @param item модель, содержащая информация о фото
     */
    public void addPhoto(ImageModel item) {
        mImageModels.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * Добавляет список фото для отображения
     *
     * @param imageModels модель, содержащая информация о фото
     */
    public void addAllPhotos(@NonNull List<ImageModel> imageModels) {
        mImageModels.addAll(imageModels);
        notifyDataSetChanged();
    }

    /**
     * @return возвращает список фото
     */
    public List<ImageModel> getImageModels() {
        return mImageModels;
    }
}
