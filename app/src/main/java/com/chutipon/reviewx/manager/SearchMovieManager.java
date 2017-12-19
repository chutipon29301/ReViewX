package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.SearchResultListDao;

import java.util.HashMap;

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
    private HashMap<String, Integer> searchResult;

    public interface onLoad {
        void onLoadSearchResult(String[] result);
    }

    public static SearchMovieManager getInstance() {
        if (instance == null) {
            instance = new SearchMovieManager();
        }
        return instance;
    }

    private SearchMovieManager() {
        searchResult = new HashMap<>();
    }

    public void search(String key, final SearchMovieManager.onLoad callback) {
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
                        String[] result = new String[value.getSearchResultInfoDaos().length];
                        for (int i = 0; i < value.getSearchResultInfoDaos().length; i++) {
                            result[i] = value.getSearchResultInfoDaos()[i].getTitle();
                            searchResult.put(value.getSearchResultInfoDaos()[i].getTitle(),value.getSearchResultInfoDaos()[i].getId());
                        }
                        callback.onLoadSearchResult(result);
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

    public int getMovieIDForKey(String key){
        return searchResult.get(key);
    }
}
