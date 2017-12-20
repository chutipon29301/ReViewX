package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.MovieReviewInfoDao;
import com.chutipon.reviewx.dao.MovieReviewListDao;
import com.facebook.AccessToken;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MovieReviewManager {
    private static final String TAG = "MovieReviewManager";
    private static MovieReviewManager instance;
    private MovieReviewListDao movieReviewListDao;

    public interface onLoad {
        void onLoadReviewComplete();
    }

    public static MovieReviewManager getInstance() {
        if (instance == null) {
            instance = new MovieReviewManager();
        }
        return instance;
    }

    private MovieReviewManager() {
        movieReviewListDao = new MovieReviewListDao();
        movieReviewListDao.setMovieReviewInfoDao(RealmManager.getInstance().findAllMovieReviewInfoDao());
    }

    public void getReview(int movieID, final MovieReviewManager.onLoad callback) {
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
                        Log.i(TAG, "onComplete: ");
                        callback.onLoadReviewComplete();
                    }
                });
    }

    public void getMyReview(final MovieReviewManager.onLoad callback) {
        HttpManager.getInstance().getApiService().getMyReview(AccessToken.getCurrentAccessToken().getUserId())
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
                        RealmManager.getInstance().storeAllMovieReviewInfoDao(movieReviewListDao);
                        callback.onLoadReviewComplete();
                    }
                });
    }

    public int getSize() {
        if (movieReviewListDao == null) {
            return 0;
        }
        if (movieReviewListDao.getMovieReviewInfoDao() == null) {
            return 0;
        }
        return movieReviewListDao.getMovieReviewInfoDao().length;
    }

    public MovieReviewInfoDao getMovieReviewInfoDaoAtIndex(int index) {
        return movieReviewListDao.getMovieReviewInfoDao()[index];
    }
}
