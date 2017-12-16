package com.chutipon.reviewx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.PreferenceAdapter;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;
    RecyclerView recyclerView;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enter = findViewById(R.id.btn_enter);
        enter.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(PreferenceActivity.this, 3));
        PreferenceAdapter.getInstance().init(PreferenceActivity.this);
        recyclerView.setAdapter(PreferenceAdapter.getInstance());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter:

                break;
            default:
                break;
        }
    }
}
