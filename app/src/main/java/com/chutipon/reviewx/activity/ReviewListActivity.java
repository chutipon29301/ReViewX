package com.chutipon.reviewx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.ReviewListFragment;

public class ReviewListActivity extends AppCompatActivity {

    private static final String TAG = "ReviewListActivity";
    private static ReviewListActivity instance;

    public static ReviewListActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_review_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ReviewListFragment.getInstance(), "ReviewListFragment")
                    .commit();
        }

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {

    }
}
