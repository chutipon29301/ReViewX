package com.chutipon.reviewx;

import android.app.Application;

import com.chutipon.reviewx.util.Contextor;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by admin on 7/12/2017 AD.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
