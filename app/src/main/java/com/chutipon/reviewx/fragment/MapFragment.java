package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MapFragment extends Fragment{
    private static MapFragment instance;

    public static MapFragment getInstance(){
        if (instance == null){
            instance = new MapFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }
}
