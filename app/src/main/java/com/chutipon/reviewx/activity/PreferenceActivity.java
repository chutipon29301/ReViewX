package com.chutipon.reviewx.activity;

import com.chutipon.reviewx.R;

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

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
