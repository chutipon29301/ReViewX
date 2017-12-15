package com.chutipon.reviewx.manager;

import com.chutipon.reviewx.dao.CheckExistUserDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 14/12/2017 AD.
 */

public class CheckExistUserManager {
    private static CheckExistUserManager instance;

    private CheckExistUserManager() {
    }

    public static CheckExistUserManager getInstance() {
        if (instance == null) {
            instance = new CheckExistUserManager();
        }
        return instance;
    }

    public void startCheckExistUser(int facebookID) {
        HttpManager.getInstance().getApiService().checkExistUser(facebookID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckExistUserDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //TODO: Start request to server
                        //TODO: Add loading fragment into activity
                    }

                    @Override
                    public void onNext(CheckExistUserDao value) {
                        //TODO: On server response
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //TODO: Handle complete event
                    }
                });
    }
}
