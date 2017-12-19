package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.MovieReviewInfoDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 12/19/2017 AD.
 */

public class ReviewInfoManager {
    private static final String TAG = "ReviewInfoManager";
    private static ReviewInfoManager instance;

    public interface onLoad {
        void onReviewLoad(MovieReviewInfoDao movieReviewInfoDao);
    }

    public static ReviewInfoManager getInstance() {
        if (instance == null) {
            instance = new ReviewInfoManager();
        }
        return instance;
    }

    private ReviewInfoManager() {
    }

    public void load(String reviewID, final ReviewInfoManager.onLoad callback) {
        HttpManager.getInstance().getApiService().getReview(reviewID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieReviewInfoDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(MovieReviewInfoDao value) {
                        Log.i(TAG, "onNext: called");
                        callback.onReviewLoad(value);
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
