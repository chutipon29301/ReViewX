package com.chutipon.reviewx.activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.MapFragment;
import com.chutipon.reviewx.fragment.MovieListFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "HomeActivity";
    Toolbar toolbar;
    Fragment currentFragnment;
    private static HomeActivity instance;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        instance = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MovieListFragment.getInstance(), "MainFragment")
                    .commit();
        }
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                redirectToPage(MainActivity.class);
            }
        };

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Button logoutBtn = findViewById(R.id.logoutBtn);
//        logoutBtn.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static HomeActivity getInstance() {
        return instance;
    }

    public void redirectToPage(Class cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tab_explore:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MovieListFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_myreview:
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.contentContainer,)
//                        .commit();
                break;
            case R.id.tab_nearby:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MapFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_readLater:
//                getSupportFragmentManager().beginTransaction()
//                        .replace()
//                        .commit();
                break;
            case R.id.tab_tutorial:
//                getSupportFragmentManager().beginTransaction()
//                        .replace()
//                        .commit();
                break;
            case R.id.tab_logout:
//                LoginManager.getInstance().logOut();
                break;
        }

    }
}
