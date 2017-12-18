package com.chutipon.reviewx.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.fragment.RandomFragment;

public class ShakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, RandomFragment.getInstance())
                    .commit();
        }
    }
}
