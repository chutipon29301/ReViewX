package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.MovieSuggestionInfoDao;
import com.chutipon.reviewx.fragment.RandomFragment;
import com.facebook.AccessToken;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class RandomMovieManager {
    private static final String TAG = "RandomMovieFragment";
    private static RandomMovieManager instance;
    private MovieSuggestionInfoDao movieSuggestionInfoDao;

    public static RandomMovieManager getInstance() {
        if (instance == null) {
            instance = new RandomMovieManager();
        }
        return instance;
    }

    private RandomMovieManager() {
    }

    public void load() {
        HttpManager.getInstance().getApiService().getRandomMovie(AccessToken.getCurrentAccessToken().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieSuggestionInfoDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(MovieSuggestionInfoDao value) {
                        Log.i(TAG, "onNext: called");
                        movieSuggestionInfoDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        RandomFragment.getInstance().loadData();
                    }
                });
    }

    public MovieSuggestionInfoDao getMovieSuggestionInfoDao() {
        return movieSuggestionInfoDao;
    }
}
