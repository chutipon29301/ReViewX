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
 * Created by admin on 15/12/2017 AD.
 */

public class LoadingFragment extends Fragment {
    private static LoadingFragment instance;

    public static LoadingFragment getInstance(){
        if (instance == null){
            instance = new LoadingFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading,container,false);
        initInstance(rootView, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {

    }
}
