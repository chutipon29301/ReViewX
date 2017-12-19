package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.adapter.MyReviewAdapter;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MyReviewFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MyReviewFragment";
    private static MyReviewFragment instance;

    public static MyReviewFragment getInstance() {
        if (instance == null) {
            instance = new MyReviewFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movielist, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        RecyclerView myReviewRecycler = rootView.findViewById(R.id.movielistRecycler);
        MyReviewAdapter.getInstance().init(getActivity().getBaseContext());
        myReviewRecycler.setAdapter(MyReviewAdapter.getInstance());
        myReviewRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        FloatingActionButton btnWrite = rootView.findViewById(R.id.btn_write);
        btnWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                HomeActivity.getInstance().triggerSearch();
                break;
            default:
                break;
        }
    }
}
