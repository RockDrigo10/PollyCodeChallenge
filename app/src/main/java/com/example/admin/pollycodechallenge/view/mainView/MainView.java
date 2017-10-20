package com.example.admin.pollycodechallenge.view.mainView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.pollycodechallenge.App;
import com.example.admin.pollycodechallenge.Injection.mainactivity.DaggerMainActivityComponent;
import com.example.admin.pollycodechallenge.Injection.mainactivity.MainActivityModule;
import com.example.admin.pollycodechallenge.R;
import com.example.admin.pollycodechallenge.view.loginview.loginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.Icepick;
import retrofit2.Retrofit;

public class MainView extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.picture)
    CircleImageView civProfilePicture;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Inject
    MainPresenter presenter;

    @Inject
    Retrofit retrofit;

    LinearLayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout activityMainSwipeRefreshLayout;
    private List<Tweet> tweets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("");
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent logInIntent = new Intent(this, loginActivity.class);
            startActivity(logInIntent);
        } else {
            tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_home));
            tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_find));
            tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_notification));
            tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_message));


            DaggerMainActivityComponent
                    .builder()
                    .mainActivityModule(new MainActivityModule())
                    .appComponent(((App) getApplicationContext()).getAppComponent())
                    .build()
                    .inject(this);
            ButterKnife.bind(this);
            initToolbar();
            initPresenter();
            Icepick.restoreInstanceState(this, savedInstanceState);
            initRecyclerView();
            String url = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
            Glide.with(getApplicationContext())
                    .load(url)
                    .into(civProfilePicture);

            presenter.makeRestCall(retrofit);
        }

    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemAnimator = new DefaultItemAnimator();
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(itemAnimator);
        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(20);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        activityMainSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                activityMainSwipeRefreshLayout.setRefreshing(true);
                presenter.makeRestCall(retrofit);
            }
        });

    }

    @Override
    public void sendInfo(List<Tweet> value) {
        FirstAdapter firstAdapter = new FirstAdapter(value);
        recycler.setAdapter(firstAdapter);
        firstAdapter.notifyDataSetChanged();

        this.tweets = value;
        activityMainSwipeRefreshLayout.setRefreshing(false);
    }
}

