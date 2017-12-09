package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.adapter.PreferenceAdapter;
import com.chutipon.reviewx.dao.GenreListDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 9/12/2017 AD.
 */

public class PreferenceManager {
    private static PreferenceManager instance;
    private GenreListDao genreListDao;

    private PreferenceManager(){
        Log.v("PreferenceManager", "PreferenceManager called");
        HttpManager.getInstance().getApiService().listGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenreListDao>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("PreferenceManager", "onSubscribe called");
            }

            @Override
            public void onNext(GenreListDao value) {
                Log.v("PreferenceManager", "onNext called");
                genreListDao = value;
            }

            @Override
            public void onError(Throwable e) {
                Log.v("PreferenceManager", "onError called");
                Log.v("PreferenceManager", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.v("PreferenceManager", "onComplete called");
                PreferenceAdapter.getInstance().notifyDataSetChanged();
            }
        });
    }

    public static PreferenceManager getInstance(){
        if (instance == null){
            instance = new PreferenceManager();
        }
        return instance;
    }

    public GenreListDao getGenreListDao() {
        return genreListDao;
    }
}
