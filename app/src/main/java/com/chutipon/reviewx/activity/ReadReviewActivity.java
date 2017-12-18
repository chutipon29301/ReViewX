package com.chutipon.reviewx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.MyReviewAdapter;
import com.chutipon.reviewx.adapter.PreferenceAdapter;

public class ReadReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static ReadReviewActivity instance;

    private final String TAG = "ReadReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_readreview);
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        instance = this;
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);=

    }

    @Override
    public void onClick(View view) {

    }

    public static ReadReviewActivity getInstance(){
        return instance;
    }

}
