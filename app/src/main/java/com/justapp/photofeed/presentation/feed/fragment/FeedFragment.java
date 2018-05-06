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
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.justapp.photofeed.R;
import com.justapp.photofeed.di.data.DataComponent;
import com.justapp.photofeed.models.local.disk.resources.ItemModel;
import com.justapp.photofeed.presentation.base.BaseFragment;
import com.justapp.photofeed.presentation.feed.adapter.PhotoFeedAdapter;
import com.justapp.photofeed.presentation.feed.adapter.RecyclerViewItemListener;
import com.justapp.photofeed.presentation.feed.presenter.FeedPresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.justapp.photofeed.routers.photoview.PhotoViewRouter;
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
    private ContentLoadingProgressBar mContentProgressBar;
    private ContentLoadingProgressBar mPaginationProgressBar;
    private PhotoFeedAdapter mPhotoFeedAdapter;
    private boolean mIsLoading;

    @Inject
    @InjectPresenter
    FeedPresenter mFeedPresenter;
    @Inject
    Picasso mPicasso;
    @Inject
    PhotoViewRouter mPhotoViewRouter;

    @NonNull
    public static FeedFragment newInstance() {
        FeedFragment feedFragment = new FeedFragment();
        feedFragment.setHasOptionsMenu(true);
        return feedFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(DataComponent.class).inject(this);
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
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_span));
        mRecyclerView.setAdapter(mPhotoFeedAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new PhotosOnScrollListener(layoutManager));
        mFeedPresenter.loadPhotos(PAGINATION_ITEMS_COUNT, mPhotoFeedAdapter.getItemCount());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.clearOnScrollListeners();
        mFeedPresenter.detachView(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_feed_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.menu_settings;
    }

    @Override
    public void showProgress(boolean loading) {
        mContentProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPhotos(List<ItemModel> list) {
        mPhotoFeedAdapter.addAllPhotos(list);
        if (mIsLoading && mContentProgressBar.getVisibility() == View.VISIBLE) {
            mIsLoading = false;
            mPaginationProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmpty() {
        mEmptyTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType) {
        ItemModel itemModel = mPhotoFeedAdapter.getItemModels().get(adapterPosition);
        mPhotoViewRouter.startPhotoView(requireActivity(), itemModel.getFile());
    }

    @ProvidePresenter
    public FeedPresenter provideFeedPresenter() {
        return mFeedPresenter;
    }

    private void initViews(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mEmptyTextView = view.findViewById(R.id.text_view_empty);
        mContentProgressBar = view.findViewById(R.id.progress_bar_item_loading);
        mPaginationProgressBar = view.findViewById(R.id.progress_bar_pagination_loading);
    }

    private class PhotosOnScrollListener extends RecyclerView.OnScrollListener {

        private final WeakReference<GridLayoutManager> mGridLayoutManager;

        private PhotosOnScrollListener(GridLayoutManager gridLayoutManager) {
            mGridLayoutManager = new WeakReference<>(gridLayoutManager);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            GridLayoutManager layoutManager = mGridLayoutManager.get();
            if (dy > 0 && layoutManager != null) {
                int childCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (!mIsLoading && (childCount + lastVisibleItem) >= totalItemCount) {
                    mIsLoading = true;
                    mPaginationProgressBar.setVisibility(View.VISIBLE);
                    mFeedPresenter.loadPhotos(PAGINATION_ITEMS_COUNT, mPhotoFeedAdapter.getItemCount());
                }
            }
        }
    }
}
