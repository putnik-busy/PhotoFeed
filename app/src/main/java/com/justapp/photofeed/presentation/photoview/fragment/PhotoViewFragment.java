package com.justapp.photofeed.presentation.photoview.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.justapp.photofeed.R;
import com.justapp.photofeed.di.data.DataComponent;
import com.justapp.photofeed.presentation.base.BaseFragment;
import com.justapp.photofeed.presentation.photoview.presenter.PhotoViewPresenter;
import com.justapp.photofeed.presentation.photoview.view.SinglePhotoView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;

/**
 * @author Sergey Rodionov
 */
public class PhotoViewFragment extends BaseFragment implements SinglePhotoView {

    public static final String TAG = "PhotoViewFragment";
    private static final String ARG_IMAGE = "ARG_IMAGE";

    @InjectPresenter
    PhotoViewPresenter mPhotoViewPresenter;
    @Inject
    Picasso mPicasso;
    private String mPhotoPath;
    private Target mTarget;

    private ImageView mPhotoView;

    public static PhotoViewFragment newInstance(@NonNull String path) {
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE, path);
        PhotoViewFragment fragment = new PhotoViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getComponent(DataComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_photo_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPhotoPath = bundle.getString(ARG_IMAGE);
        }
        initViews(view);
        initToolBar();
        mPhotoViewPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPhotoViewPresenter.destroyView(this);
        hideToolBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_photo_view_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_share:
                // do stuff
                return true;
        }

        return false;
    }

    @ProvidePresenter
    public PhotoViewPresenter provideSinglePhotoViewPresenter() {
        return new PhotoViewPresenter();
    }

    private void initViews(@NonNull View view) {
        mPhotoView = view.findViewById(R.id.full_photo_view);
    }

    @Override
    public void showPhoto() {
        mTarget = new TargetPhoto();
        Drawable placeholderDrawable = getDrawable(getResources(), R.drawable.anim_ic_spinner_black, null);
        Drawable errorDrawable = getDrawable(getResources(), R.drawable.ic_error_black, null);
        mPicasso.load(mPhotoPath)
                .placeholder(placeholderDrawable)
                .error(errorDrawable)
                .into(mTarget);
    }

    private void hideToolBar() {
        AppCompatActivity activity = ((AppCompatActivity) requireActivity());
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            activity.setSupportActionBar(toolbar);
            actionBar.setTitle(R.string.feed);
        }
    }

    private void initToolBar() {
        AppCompatActivity activity = ((AppCompatActivity) requireActivity());
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            activity.setSupportActionBar(toolbar);
            actionBar.setTitle("");
        }
    }

    private class TargetPhoto implements Target {

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mPhotoView.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mPhotoView.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            if (placeHolderDrawable instanceof Animatable) {
                mPhotoView.setImageDrawable(placeHolderDrawable);
                ((Animatable) placeHolderDrawable).start();
            }
        }
    }

}
