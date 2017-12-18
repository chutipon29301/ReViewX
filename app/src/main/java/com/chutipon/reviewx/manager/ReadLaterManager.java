package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.facebook.AccessToken;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 18/12/2017 AD.
 */

public class ReadLaterManager {
    private static final String TAG = "ReadLaterManager";
    private static ReadLaterManager instance;

    public interface onLoad {
        void onLoadComplete();
    }

    public static ReadLaterManager getInstance() {
        if (instance == null) {
            instance = new ReadLaterManager();
        }
        return instance;
    }

    private ReadLaterManager() {
    }

    public void addReadLater(String reviewID, final ReadLaterManager.onLoad callback) {
        HttpManager.getInstance().getApiService().addReadLater(AccessToken.getCurrentAccessToken().getUserId(), reviewID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponseDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(GeneralResponseDao value) {
                        Log.i(TAG, "onNext: called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        callback.onLoadComplete();
                    }
                });
    }

    public void deleteReadLater(String readLaterID, final ReadLaterManager.onLoad callback){
        HttpManager.getInstance().getApiService().deleteReadLater(readLaterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponseDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(GeneralResponseDao value) {
                        Log.i(TAG, "onNext: called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        callback.onLoadComplete();
                    }
                });
    }
}
