package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.AddReviewDao;
import com.chutipon.reviewx.dao.GeneralResponseDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 18/12/2017 AD.
 */

public class AddReviewManager {
    private static final String TAG = "AddReviewManager";
    private static AddReviewManager instance;

    public interface onLoadComplete {
        void onLoadComplete();
    }

    public static AddReviewManager getInstance() {
        if (instance == null) {
            instance = new AddReviewManager();
        }
        return instance;
    }

    private AddReviewManager() {
    }

    public void addReview(AddReviewDao addReviewDao, final AddReviewManager.onLoadComplete callback) {
        HttpManager.getInstance().getApiService().addReview(addReviewDao)
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
