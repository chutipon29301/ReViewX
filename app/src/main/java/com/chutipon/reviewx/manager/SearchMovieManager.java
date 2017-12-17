package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.SearchResultInfoDao;
import com.chutipon.reviewx.dao.SearchResultListDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class SearchMovieManager {
    private static final String TAG = "SearchMovieManager";
    private static SearchMovieManager instance;
    private SearchResultListDao searchResultListDao;

    private static SearchMovieManager getInstance() {
        if (instance == null) {
            instance = new SearchMovieManager();
        }
        return instance;
    }

    private SearchMovieManager() {
    }

    public void search(String key) {
        HttpManager.getInstance().getApiService().searchMovie(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResultListDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(SearchResultListDao value) {
                        Log.i(TAG, "onNext: called");
                        searchResultListDao = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: called");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        //TODO: Notify caller adapter
                    }
                });
    }

    public int getSize() {
        if (searchResultListDao == null) {
            return 0;
        }
        if (searchResultListDao.getSearchResultInfoDaos() == null) {
            return 0;
        }
        return searchResultListDao.getSearchResultInfoDaos().length;
    }

    public SearchResultInfoDao getSearchResultInfoDaoAtIndex(int index) {
        return searchResultListDao.getSearchResultInfoDaos()[index];
    }

}
