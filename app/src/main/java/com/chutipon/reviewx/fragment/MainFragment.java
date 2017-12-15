package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;

/**
 * Created by admin on 7/12/2017 AD.
 */

public class MainFragment extends Fragment {

    private static MainFragment instance;

    public static MainFragment getInstance(){
        if (instance == null){
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(savedInstanceState,rootView);
        return rootView;
    }

    private void initInstance(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO: To be implement
    }

}
