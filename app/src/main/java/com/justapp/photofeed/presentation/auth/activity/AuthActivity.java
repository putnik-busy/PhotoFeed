package com.justapp.photofeed.presentation.auth.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.justapp.photofeed.R;
import com.justapp.photofeed.di.auth.AuthInjector;
import com.justapp.photofeed.presentation.auth.presenter.AuthPresenter;
import com.justapp.photofeed.presentation.auth.view.AuthView;
import com.justapp.photofeed.routers.auth.AuthRouter;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
public class AuthActivity extends MvpAppCompatActivity implements AuthView {

    @Inject
    @InjectPresenter
    AuthPresenter mAuthPresenter;
    @Inject
    AuthRouter mAuthRouter;

    private Button mAuthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        AuthInjector.createAuthComponent().inject(this);
        initViews();
        mAuthPresenter.attachView(this);
        setupToolBar();

        Uri data = getIntent().getData();
        if (data != null) {
            mAuthPresenter.saveToken(data.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuthPresenter.destroyView(this);
        AuthInjector.clearAuthModule();
    }

    @Override
    public void startAuth(String url) {
        mAuthRouter.startAuth(this, url);
    }

    @Override
    public void enableAuthButton(boolean enable) {
        mAuthButton.setEnabled(enable);
    }

    @Override
    public void startPhotoFeed() {
        mAuthRouter.startPhotoFeed(this);
    }

    private void initViews() {
        mAuthButton = findViewById(R.id.auth_button);
        mAuthButton.setOnClickListener(view -> mAuthPresenter.enter());
    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(R.string.title_auth_text);
        }
    }

}
