package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.ReviewListAdapter;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReviewListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView reviewListRecycler;
    Button btnmore;
    public static ReviewListFragment getInstance() {
        if(instance==null){
            instance = new ReviewListFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_reviewlist,container,false);
       initInstance(rootView);
        return rootView;
    }


    private void initInstance(View rootView) {

        reviewListRecycler = rootView.findViewById(R.id.reviewlistRecycler);
        btnmore = rootView.findViewById(R.id.btn_more);
        ReviewListAdapter.getInstance().init(getActivity().getBaseContext());

        reviewListRecycler.setAdapter(ReviewListAdapter.getInstance());
        btnmore.setOnClickListener(this);
        reviewListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


    }



    private static ReviewListFragment instance;

    public ReviewListFragment(){
    }


    @Override
    public void onClick(View view) {
        if(view==btnmore){

        }
    }
}
