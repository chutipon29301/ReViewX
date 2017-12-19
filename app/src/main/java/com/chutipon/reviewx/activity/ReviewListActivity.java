package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.ReviewListFragment;
import com.chutipon.reviewx.manager.MovieInfoManager;

public class ReviewListActivity extends AppCompatActivity implements MovieInfoManager.onLoad{

    private static final String TAG = "ReviewListActivity";
    private static ReviewListActivity instance;
    private int movieID;

    public static ReviewListActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_review_list);

        Intent intent = getIntent();
        movieID = intent.getIntExtra("movieID", -1);
        Log.d(TAG, "onCreate: movieID " + movieID);
        if (movieID == -1) {
            finish();
        }

        if (savedInstanceState == null) {
            ReviewListFragment.getInstance().getArguments().putInt("movieID", movieID);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ReviewListFragment.getInstance(), "ReviewListFragment")
                    .commit();
        }

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        MovieInfoManager.getInstance().load(movieID, this);
    }

    @Override
    public void onLoadMovieInfo() {
        ReviewListFragment.getInstance().onLoadMovieInfo();
    }

    public void redirect(Class cls, String key, String value) {
        Intent intent = new Intent(ReviewListActivity.this, cls);
        intent.putExtra(key, value);
        startActivity(intent);
    }
}
