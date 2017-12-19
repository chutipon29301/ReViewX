package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.chutipon.reviewx.dao.PreferenceInfoDao;
import com.chutipon.reviewx.dao.PreferenceListDao;
import com.chutipon.reviewx.util.PreferenceUtil;
import com.facebook.AccessToken;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 16/12/2017 AD.
 */

public class AddUserManager {
    private static final String TAG = "AddUserManager";
    private static AddUserManager instance;

    public interface onLoad{
        void onLoadComplate();
    }

    private AddUserManager() {
    }

    public static AddUserManager getInstance() {
        if (instance == null) {
            instance = new AddUserManager();
        }
        return instance;
    }

    public void addUser(final AddUserManager.onLoad callback) {
        PreferenceInfoDao preferenceInfoDao = new PreferenceInfoDao();
        ArrayList<Integer> like = new ArrayList<>();
        ArrayList<Integer> dislike = new ArrayList<>();
        for (int i = 0; i < PreferenceUtil.getInstance().getStateSize(); i++) {
            if (PreferenceUtil.getInstance().getStateAt(i)) {
                like.add(PreferenceManager.getInstance().getGenreListDao().getGenres()[i].getGenreID());
            } else {
                dislike.add(PreferenceManager.getInstance().getGenreListDao().getGenres()[i].getGenreID());
            }
        }
        preferenceInfoDao.setLike(like);
        preferenceInfoDao.setDislike(dislike);

        PreferenceListDao preferenceListDao = new PreferenceListDao();
        preferenceListDao.setFacebookId(AccessToken.getCurrentAccessToken().getUserId());
        preferenceListDao.setPreference(preferenceInfoDao);

        HttpManager.getInstance()
                .getApiService()
                .addUser(preferenceListDao)
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
                        callback.onLoadComplate();
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
