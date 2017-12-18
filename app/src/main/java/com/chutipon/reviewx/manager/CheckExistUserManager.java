package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.CheckExistUserDao;
import com.facebook.AccessToken;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 14/12/2017 AD.
 */

public class CheckExistUserManager {
    private static final String TAG = "CheckExistUserManager";
    private static CheckExistUserManager instance;

    public interface onLoad{
        void onCheckExistResult(boolean exist);
    }

    private CheckExistUserManager() {
    }

    public static CheckExistUserManager getInstance() {
        if (instance == null) {
            instance = new CheckExistUserManager();
        }
        return instance;
    }

    public void startCheckExistUser(final CheckExistUserManager.onLoad callback) {
        HttpManager.getInstance().getApiService().checkExistUser(AccessToken.getCurrentAccessToken().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckExistUserDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(CheckExistUserDao value) {
                        Log.i(TAG, "onNext: called");
                        callback.onCheckExistResult(value.isExist());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                    }
                });
    }
}
