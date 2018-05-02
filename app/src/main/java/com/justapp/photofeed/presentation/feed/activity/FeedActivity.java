package com.justapp.photofeed.presentation.feed.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.justapp.photofeed.R;
import com.justapp.photofeed.di.data.DataInjector;
import com.justapp.photofeed.presentation.feed.fragment.FeedFragment;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        DataInjector.createDataComponent().inject(this);
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
            fragmentManager.beginTransaction()
                    .add(R.id.container, FeedFragment.getInstance(), FeedFragment.TAG)
                    .commit();
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

}
