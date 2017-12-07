package com.chutipon.reviewx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.MainFragment;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.getInstance(), "MainFragment")
                    .commit();
        }
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
