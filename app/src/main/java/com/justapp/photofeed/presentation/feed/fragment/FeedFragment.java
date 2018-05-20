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
import com.justapp.photofeed.models.local.disk.resources.ImageModel;
import com.justapp.photofeed.presentation.base.BaseFragment;
import com.justapp.photofeed.presentation.feed.adapter.PhotoFeedAdapter;
import com.justapp.photofeed.presentation.feed.adapter.RecyclerViewItemListener;
import com.justapp.photofeed.presentation.feed.presenter.FeedPresenter;
import com.justapp.photofeed.presentation.feed.view.PhotoView;
import com.justapp.photofeed.routers.photoview.PhotoViewRouter;
import com.justapp.photofeed.utils.GridDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Фрагмент ленты фотографий
 *
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

    /**
     * Создает инстанс {@link FeedFragment}
     *
     * @return инстанс {@link FeedFragment}
     */
    @NonNull
    public static FeedFragment newInstance() {
        FeedFragment feedFragment = new FeedFragment();
        feedFragment.setHasOptionsMenu(true);
        return feedFragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(DataComponent.class).inject(this);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_feed, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        initViews(view);
        mFeedPresenter.attachView(this);
        mPhotoFeedAdapter = new PhotoFeedAdapter(this, mPicasso);
        int spanCount = getResources().getInteger(R.integer.grid_span);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), spanCount);
        mRecyclerView.setAdapter(mPhotoFeedAdapter);
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(requireContext(), spanCount));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new PhotosOnScrollListener(layoutManager));
        mFeedPresenter.loadPhotos(PAGINATION_ITEMS_COUNT, mPhotoFeedAdapter.getItemCount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.clearOnScrollListeners();
        mFeedPresenter.detachView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_feed_menu, menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.menu_settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showProgress(boolean loading) {
        mContentProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showPhotos(List<ImageModel> list) {
        mPhotoFeedAdapter.addAllPhotos(list);
        if (mIsLoading && mContentProgressBar.getVisibility() == View.VISIBLE) {
            mIsLoading = false;
            mPaginationProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showEmpty() {
        mEmptyTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showErrorMessage(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType) {
        ImageModel imageModel = mPhotoFeedAdapter.getImageModels().get(adapterPosition);
        mPhotoViewRouter.startPhotoView(requireActivity(), imageModel.getFile());
    }

    /**
     * Провайдим презентер для Moxy
     *
     * @return презентер {@link FeedPresenter}
     */
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
