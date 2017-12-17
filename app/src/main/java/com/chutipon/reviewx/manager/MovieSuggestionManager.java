package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.adapter.MovieListAdapter;
import com.chutipon.reviewx.dao.MovieSuggestionInfoDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.facebook.AccessToken;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MovieSuggestionManager {
    private static final String TAG = "MovieSuggestionManager";
    private static MovieSuggestionManager instance;
    private MovieSuggestionListDao movieSuggestionListDao;

    public static MovieSuggestionManager getInstance() {
        if (instance == null) {
            instance = new MovieSuggestionManager();
        }
        return instance;
    }

    private MovieSuggestionManager() {
        HttpManager.getInstance().getApiService().listMovieSuggestion(AccessToken.getCurrentAccessToken().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieSuggestionListDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(MovieSuggestionListDao value) {
                        Log.i(TAG, "onNext: called");
                        movieSuggestionListDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: called");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        MovieListAdapter.getInstance().notifyDataSetChanged();
                    }
                });
    }

    public int getSize(){
        if (movieSuggestionListDao == null){
            return  0;
        }
        if (movieSuggestionListDao.getMovieSuggestionInfoDao() == null){
            return 0;
        }
        return movieSuggestionListDao.getMovieSuggestionInfoDao().length;
    }

    public MovieSuggestionInfoDao getMovieSuggestionInfoAtIndex(int index){
        return movieSuggestionListDao.getMovieSuggestionInfoDao()[index];
    }

}