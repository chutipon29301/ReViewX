package com.chutipon.reviewx;

import android.app.Application;

import com.chutipon.reviewx.util.Contextor;

/**
 * Created by admin on 7/12/2017 AD.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}
