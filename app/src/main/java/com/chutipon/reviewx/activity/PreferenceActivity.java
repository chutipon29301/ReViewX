package com.chutipon.reviewx.activity;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.PreferenceAdapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

public class PreferenceActivity extends AppCompatActivity {
    Toolbar toolbar;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        initinstance(savedInstanceState);
    }

    private void initinstance(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        gridView = findViewById(R.id.gridView);

        setSupportActionBar(toolbar);

        gridView.setAdapter(new PreferenceAdapter());
    }
}
