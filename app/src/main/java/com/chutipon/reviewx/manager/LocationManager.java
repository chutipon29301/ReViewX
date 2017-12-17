package com.chutipon.reviewx.manager;

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
    private static LocationManager instance;
    private LocationListDao locationListDao;

    public static LocationManager getInstance() {
        if (instance == null) {
            instance = new LocationManager();
        }
        return instance;
    }

    private LocationManager() {
        HttpManager.getInstance().getApiService().getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationListDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationListDao value) {
                        locationListDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LocationInfoDao getInfoAtIndex(int index) {
        return locationListDao.getLocations().get(index);
    }

    public int getLocationsSize() {
        if (locationListDao == null) {
            return 0;
        }
        if (locationListDao.getLocations() == null) {
            return 0;
        }
        return locationListDao.getLocations().size();
    }
}
