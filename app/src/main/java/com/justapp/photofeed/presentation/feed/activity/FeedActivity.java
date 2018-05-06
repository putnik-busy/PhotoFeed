package com.justapp.photofeed.presentation.feed.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.justapp.photofeed.R;
import com.justapp.photofeed.di.application.HasComponent;
import com.justapp.photofeed.di.data.DataComponent;
import com.justapp.photofeed.di.data.DataInjector;
import com.justapp.photofeed.presentation.feed.fragment.FeedFragment;
import com.justapp.photofeed.utils.ActivityUtils;

public class FeedActivity extends AppCompatActivity implements HasComponent<DataComponent> {

    private DataComponent mDataComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDataComponent = DataInjector.createDataComponent();
        mDataComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        setupToolBar();
        initFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            DataInjector.clearDataModule();
        }
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment == null) {
            ActivityUtils.addFragmentInActivity(fragmentManager, FeedFragment.newInstance(),
                    R.id.container, FeedFragment.TAG);
        }
    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(R.string.feed);
        }
    }

    @Override
    public DataComponent getComponent() {
        return mDataComponent;
    }
}
