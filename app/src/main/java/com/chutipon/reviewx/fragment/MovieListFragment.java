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
import android.widget.Button;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;

import com.chutipon.reviewx.activity.SearchActivity;
import com.chutipon.reviewx.adapter.MovieListAdapter;
import com.scalified.fab.ActionButton;

import at.markushi.ui.CircleButton;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView movieListRecycler;
    FloatingActionButton btnwrite;

    public static MovieListFragment getInstance() {
        if(instance==null){
            instance = new MovieListFragment();
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

        movieListRecycler = rootView.findViewById(R.id.movielistRecycler);
        btnwrite = rootView.findViewById(R.id.btn_write);
        btnwrite.setOnClickListener(this);
        MovieListAdapter.getInstance().init(getActivity().getBaseContext());

        movieListRecycler.setAdapter(MovieListAdapter.getInstance());
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));



    }



    private static MovieListFragment instance;

    public MovieListFragment(){

    }


    @Override
    public void onClick(View view) {
        if(view==btnwrite){
            HomeActivity.getInstance().redirect(SearchActivity.class);
        }
    }
}
