package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.LocationInfoDao;
import com.chutipon.reviewx.dao.LocationListDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class LocationManager {
    private static final String TAG = "LocationManager";
    private static LocationManager instance;
    private LocationListDao locationListDao;

    public static LocationManager getInstance() {
        if (instance == null) {
            instance = new LocationManager();
        }
        return instance;
    }

    private LocationManager() {
        if (RealmManager.getInstance().findAllLocationInfoDao() != null) {
            locationListDao = new LocationListDao();
            locationListDao.setLocations(RealmManager.getInstance().findAllLocationInfoDao());
        }
    }

    public void load() {
        HttpManager.getInstance().getApiService().getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationListDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(LocationListDao value) {
                        Log.i(TAG, "onNext: called");
                        locationListDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        RealmManager.getInstance().storeAllLocationInfoDao(locationListDao);
                        Log.i(TAG, "onComplete: called");
                    }
                });
    }

    public LocationInfoDao getInfoAtIndex(int index) {
        return locationListDao.getLocations()[index];
    }

    public int getLocationsSize() {
        if (locationListDao == null) {
            return 0;
        }
        if (locationListDao.getLocations() == null) {
            return 0;
        }
        return locationListDao.getLocations().length;
    }
}
