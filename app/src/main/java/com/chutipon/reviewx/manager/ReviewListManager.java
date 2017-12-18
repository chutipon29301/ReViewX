package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.MovieReviewInfoDao;
import com.chutipon.reviewx.dao.MovieReviewListDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 18/12/2017 AD.
 */

public class ReviewListManager {
    private static final String TAG = "ReviewListManager";
    private static ReviewListManager instance;
    private MovieReviewListDao movieReviewListDao;

    public interface OnLoad {
        void onLoadComplete();
    }

    public static ReviewListManager getInstance() {
        if (instance == null) {
            instance = new ReviewListManager();
        }
        return instance;
    }

    public void load(int movieID, final ReviewListManager.OnLoad callback) {
        HttpManager.getInstance().getApiService().getMovieReview(movieID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieReviewListDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(MovieReviewListDao value) {
                        Log.i(TAG, "onNext: called");
                        movieReviewListDao = value;
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

    public int getSize(){
        return movieReviewListDao.getMovieReviewInfoDao().length;
    }

    public MovieReviewInfoDao getMovieReviewInfoAtIndex(int index){
        return movieReviewListDao.getMovieReviewInfoDao()[index];
    }

}
