package com.justapp.photofeed.presentation.feed.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.justapp.photofeed.R;
import com.justapp.photofeed.di.data.DataComponent;
import com.justapp.photofeed.di.data.DataInjector;
import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.presentation.auth.presenter.AuthPresenter;
import com.justapp.photofeed.presentation.base.BaseFragment;
import com.justapp.photofeed.presentation.feed.adapter.PhotoFeedAdapter;
import com.justapp.photofeed.presentation.feed.adapter.RecyclerViewItemListener;
import com.justapp.photofeed.presentation.feed.presenter.FeedPresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
public class FeedFragment extends BaseFragment implements PhotoView, RecyclerViewItemListener {

    public static final String TAG = "FeedFragment";
    private static final int PAGINATION_ITEMS_COUNT = 10;

    private RecyclerView mRecyclerView;
    private TextView mEmptyTextView;
    private ContentLoadingProgressBar mProgressBar;
    private PhotoFeedAdapter mPhotoFeedAdapter;
    private boolean mIsLoading;

    @Inject
    @InjectPresenter
    FeedPresenter mFeedPresenter;
    @Inject
    Picasso mPicasso;

    @NonNull
    public static FeedFragment getInstance() {
        FeedFragment feedFragment = new FeedFragment();
        feedFragment.setHasOptionsMenu(true);
        return feedFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataInjector.createDataComponent().inject(this);
       // getComponent(DataComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        initViews(view);
        mFeedPresenter.attachView(this);
        mPhotoFeedAdapter = new PhotoFeedAdapter(this, mPicasso);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), R.integer.grid_span);
        mRecyclerView.setAdapter(mPhotoFeedAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new PhotosOnScrollListener(layoutManager));
    }

    @Override
    public void onResume() {
        super.onResume();
        mFeedPresenter.loadPhotos(PAGINATION_ITEMS_COUNT, mPhotoFeedAdapter.getItemCount());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFeedPresenter.detachView(this);
        DataInjector.clearDataModule();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_feed_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_share:
                // do stuff, like showing settings fragment
                return true;

            case R.id.menu_settings:
                // do stuff, like showing settings fragment
                return true;
        }

        return false;
    }

    @Override
    public void showProgress(boolean loading) {
        mProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPhotos(List<ItemModel> list) {
        mPhotoFeedAdapter.addAllPhotos(list);
        showEmpty(mPhotoFeedAdapter.getItemCount() == 0);
        if (mIsLoading && mProgressBar.getVisibility() == View.VISIBLE) {
            mIsLoading = false;
            showProgress(false);
        }
    }

    @Override
    public void showEmpty(boolean isEmpty) {
        mEmptyTextView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType) {

    }

    @ProvidePresenter
    public FeedPresenter provideFeedPresenter() {
        return mFeedPresenter;
    }

    private void initViews(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mEmptyTextView = view.findViewById(R.id.text_view_empty);
        mProgressBar = view.findViewById(R.id.progress_bar_loading);
    }

    private class PhotosOnScrollListener extends RecyclerView.OnScrollListener {

        private final WeakReference<GridLayoutManager> mGridLayoutManager;
        private int mItemVisible;
        private int mItemTotal;
        private int mItemPast;

        private PhotosOnScrollListener(GridLayoutManager gridLayoutManager) {
            mGridLayoutManager = new WeakReference<>(gridLayoutManager);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            GridLayoutManager layoutManager = mGridLayoutManager.get();
            if (dy > 0 && layoutManager != null) {
                mItemVisible = layoutManager.getChildCount();
                mItemTotal = layoutManager.getItemCount();
                mItemPast = layoutManager.findFirstVisibleItemPosition();

                if (!mIsLoading && (mItemVisible + mItemPast) >= mItemTotal) {
                    mIsLoading = true;
                    showProgress(true);
                    mFeedPresenter.loadPhotos(PAGINATION_ITEMS_COUNT,
                            mPhotoFeedAdapter.getItemCount());
                }
            }
        }
    }
}
