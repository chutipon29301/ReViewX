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
import com.chutipon.reviewx.activity.SearchActivity;
import com.chutipon.reviewx.adapter.MyReviewAdapter;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MyReviewFragment extends Fragment implements View.OnClickListener {
    private RecyclerView myReviewRecycler;

    FloatingActionButton btnwrite;
    public static MyReviewFragment getInstance() {
        if(instance==null){
            instance = new MyReviewFragment();
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
       View rootView = inflater.inflate(R.layout.fragment_movielist,container,false);
       initInstance(rootView);
        return rootView;
    }


    private void initInstance(View rootView) {

       myReviewRecycler = rootView.findViewById(R.id.movielistRecycler);

        MyReviewAdapter.getInstance().init(getActivity().getBaseContext());

        myReviewRecycler.setAdapter(MyReviewAdapter.getInstance());

        myReviewRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        btnwrite = rootView.findViewById(R.id.btn_write);
        btnwrite.setOnClickListener(this);

    }



    private static MyReviewFragment instance;

    public MyReviewFragment(){
    }


    @Override
    public void onClick(View view) {
            if(view==btnwrite){
                HomeActivity.getInstance().redirect(SearchActivity.class);
            }
    }
}
