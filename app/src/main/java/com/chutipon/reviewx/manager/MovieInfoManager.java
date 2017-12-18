package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.dao.MovieInfoDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 18/12/2017 AD.
 */

public class MovieInfoManager {
    private static final String TAG = "MovieInfoManager";
    private static MovieInfoManager instance;
    private MovieInfoDao movieInfoDao;

    public interface onLoad {
        void onLoadMovieInfo();
    }

    public static MovieInfoManager getInstance() {
        if (instance == null) {
            instance = new MovieInfoManager();
        }
        return instance;
    }

    private MovieInfoManager() {
    }

    public void load(final int movieID, final MovieInfoManager.onLoad callback) {
        HttpManager.getInstance().getApiService().getMovieInfo(movieID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfoDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(MovieInfoDao value) {
                        Log.i(TAG, "onNext: called");
                        movieInfoDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        callback.onLoadMovieInfo();
                    }
                });
    }

    public MovieInfoDao getMovieInfoDao() {
        return movieInfoDao;
    }
}
