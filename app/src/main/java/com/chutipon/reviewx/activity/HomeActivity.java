package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.MapFragment;
import com.chutipon.reviewx.fragment.MovieListFragment;
import com.chutipon.reviewx.fragment.SearchFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.squareup.seismic.ShakeDetector;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ShakeDetector.Listener {
    private static String TAG = "HomeActivity";
    private static HomeActivity instance;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private AccessTokenTracker accessTokenTracker;
    private static boolean shakeActivityRunning = false;
    private static final int START_SHAKE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
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

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.tab_explore).setOnClickListener(this);
        findViewById(R.id.tab_myreview).setOnClickListener(this);
        findViewById(R.id.tab_nearby).setOnClickListener(this);
        findViewById(R.id.tab_readLater).setOnClickListener(this);
        findViewById(R.id.tab_tutorial).setOnClickListener(this);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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
        switch (view.getId()) {
            case R.id.tab_explore:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MovieListFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_nearby:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MapFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_myreview:
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.contentContainer,)
//                        .commit();
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case START_SHAKE_ACTIVITY:
                shakeActivityRunning = false;
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public void Search() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, SearchFragment.getInstance())
                .commit();
    }

    @Override
    public void hearShake() {
        Log.d(TAG, "OnShake: called");
        Log.d(TAG, "OnShake: " + shakeActivityRunning);
        if (!shakeActivityRunning) {
            shakeActivityRunning = true;
            Intent intent = new Intent(HomeActivity.this, ShakeActivity.class);
            startActivityForResult(intent, START_SHAKE_ACTIVITY);
        }
    }
}
