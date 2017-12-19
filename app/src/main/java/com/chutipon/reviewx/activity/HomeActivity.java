package com.chutipon.reviewx.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.Tutorial;
import com.chutipon.reviewx.fragment.MapFragment;
import com.chutipon.reviewx.fragment.MovieListFragment;
import com.chutipon.reviewx.fragment.MyReviewFragment;
import com.chutipon.reviewx.fragment.ReadLaterFragment;
import com.chutipon.reviewx.manager.SearchMovieManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.Profile;
import com.squareup.seismic.ShakeDetector;

import br.com.mauker.materialsearchview.MaterialSearchView;
import me.toptas.fancyshowcase.FancyShowCaseView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ShakeDetector.Listener, SearchMovieManager.onLoad {
    private static final int START_SHAKE_ACTIVITY = 1;
    private static boolean shakeActivityRunning = false;
    private static String TAG = "HomeActivity";
    private static HomeActivity instance;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private AccessTokenTracker accessTokenTracker;
    private MaterialSearchView searchView;

    public static HomeActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        instance = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MovieListFragment.getInstance(), "MovieListFragment")
                    .commit();
        }
        initListener();
        initInstance(savedInstanceState);
    }

    private void initListener() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                redirectToPage(MainActivity.class);
            }
        };

        new ShakeDetector(this).start((SensorManager) getSystemService(SENSOR_SERVICE));
    }

    private void initInstance(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Tutorial.getInstance().showMenuTutorial(HomeActivity.this);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (MovieListFragment.getInstance().isVisible()) {
                    MovieListFragment.getInstance().showMainTutorial();
                }

            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.tab_explore).setOnClickListener(this);
        findViewById(R.id.tab_myreview).setOnClickListener(this);
        findViewById(R.id.tab_nearby).setOnClickListener(this);
        findViewById(R.id.tab_readLater).setOnClickListener(this);
        findViewById(R.id.tab_tutorial).setOnClickListener(this);

        TextView username = findViewById(R.id.username);
        username.setText(Profile.getCurrentProfile().getName());


        searchView = findViewById(R.id.search_view);
        searchView.clearAll();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchMovieManager.getInstance().search(newText,HomeActivity.this);
                return false;
            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectMovie = searchView.getSuggestionAtPosition(i);
//                Log.d(TAG, "onItemClick: name " + selectMovie);
//                Log.d(TAG, "onItemClick: id " + SearchMovieManager.getInstance().getMovieIDForKey(selectMovie));
                Intent intent = new Intent(HomeActivity.this, WriteReviewActivity.class);
                intent.putExtra("movieID",SearchMovieManager.getInstance().getMovieIDForKey(selectMovie));
                startActivity(intent);
            }
        });
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
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void redirectToPage(Class cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void redirect(Class cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        startActivity(intent);
    }

    public void redirect(Class cls, String key, int value) {
        Intent intent = new Intent(HomeActivity.this, cls);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void redirect(Class cls, String key, String value) {
        Intent intent = new Intent(HomeActivity.this, cls);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: " + view.getId());
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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MyReviewFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_readLater:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, ReadLaterFragment.getInstance())
                        .commit();
                break;
            case R.id.tab_tutorial:
                FancyShowCaseView.resetAllShowOnce(this);
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

    @Override
    public void hearShake() {
        Log.d(TAG, "OnShake: called");
        Log.d(TAG, "OnShake: isActivityRunning = " + shakeActivityRunning);
        if (!shakeActivityRunning) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(150);
            shakeActivityRunning = true;
            Intent intent = new Intent(HomeActivity.this, ShakeActivity.class);
            startActivityForResult(intent, START_SHAKE_ACTIVITY);
        }
    }

    public void triggerSearch() {
        searchView.openSearch();
    }

    @Override
    public void onBackPressed() {
        if (searchView.isOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onLoadSearchResult(String[] result) {
        searchView.addSuggestions(result);
    }
}
