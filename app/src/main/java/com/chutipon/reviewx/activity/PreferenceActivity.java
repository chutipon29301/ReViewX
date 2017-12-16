package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.PreferenceAdapter;
import com.chutipon.reviewx.manager.AddUserManager;
import com.chutipon.reviewx.util.PreferenceUtil;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private static PreferenceActivity instance;

    private final String TAG = "PreferenceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        instance = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button enter = findViewById(R.id.btn_enter);
        enter.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(PreferenceActivity.this, 3));
        PreferenceAdapter.getInstance().init(PreferenceActivity.this);
        recyclerView.setAdapter(PreferenceAdapter.getInstance());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter:
                AddUserManager.getInstance().addUser();
                break;
            default:
                break;
        }
    }

    public static PreferenceActivity getInstance(){
        return instance;
    }

    public void redirectToPage(Class cls) {
        Intent intent = new Intent(PreferenceActivity.this, cls);
        startActivity(intent);
        finish();
    }
}
