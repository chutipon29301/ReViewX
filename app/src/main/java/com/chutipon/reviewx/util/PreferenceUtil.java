package com.chutipon.reviewx.util;

import android.util.Log;

import com.chutipon.reviewx.dao.PreferenceInfoDao;
import com.chutipon.reviewx.dao.PreferenceListDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 12/15/2017 AD.
 */

public class PreferenceUtil {
    private ArrayList<Integer> like;
    private ArrayList<Integer> dislike;
    private PreferenceInfoDao preferenceInfoDao;

    public static PreferenceUtil getInstance(){
        if (instance == null){
            instance = new PreferenceUtil();
        }
        return instance;
    }
    private PreferenceUtil(){
            like = new ArrayList<>();
            dislike = new ArrayList<>();
            preferenceInfoDao = new PreferenceInfoDao();
    }
    public void addLike(int likeId){

                like.add(likeId);
                preferenceInfoDao.setLike(convertIntegers(like));

        Log.d("add","like size"+like.size());
    }
    public void addDislike(int dislikeId){
                dislike.add(dislikeId);
                preferenceInfoDao.setDislike(convertIntegers(dislike));
        }



    private static PreferenceUtil instance;
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }


}
