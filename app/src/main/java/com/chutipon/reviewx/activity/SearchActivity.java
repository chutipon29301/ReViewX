package com.chutipon.reviewx.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.SearchAdapter;

public class SearchActivity extends AppCompatActivity {
    private static String TAG = "SearchActivity";
    private static SearchActivity instance;
    private RecyclerView searchRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        instance = this;
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {

        searchRecycler = findViewById(R.id.searchRecycler);

        SearchAdapter.getInstance().init(getBaseContext());

        searchRecycler.setAdapter(SearchAdapter.getInstance());
        searchRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
    }


    public static SearchActivity getInstance() {
        return instance;
    }




}
