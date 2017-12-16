package com.chutipon.reviewx.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by admin on 16/12/2017 AD.
 */

public class MapFragment extends Fragment{
    private static MapFragment instance;

    public static MapFragment getInstance(){
        if (instance == null){
            instance = new MapFragment();
        }
        return instance;
    }


}
