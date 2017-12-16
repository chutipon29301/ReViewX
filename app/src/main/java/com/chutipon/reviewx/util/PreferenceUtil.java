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

    private static PreferenceUtil instance;
    private boolean[] state;

    public static PreferenceUtil getInstance() {
        if (instance == null) {
            instance = new PreferenceUtil();
        }
        return instance;
    }

    private PreferenceUtil() {
    }

    public void setStateSize(int size){
        state = new boolean[size];
    }

    public void setValue(int index, boolean value){
        state[index] = value;
    }

    public boolean getStateAt(int index){
        return state[index];
    }

    public int getStateSize(){
        return state.length;
    }

}
