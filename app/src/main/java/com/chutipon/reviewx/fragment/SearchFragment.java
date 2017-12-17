package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;

import com.chutipon.reviewx.adapter.MovieListAdapter;
import com.chutipon.reviewx.adapter.SearchAdapter;

import at.markushi.ui.CircleButton;

import static com.chutipon.reviewx.R.id.movielistRecycler;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class SearchFragment extends Fragment{
    private RecyclerView searchRecycler;


    public static SearchFragment getInstance() {
        if(instance==null){
            instance = new SearchFragment();
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
       View rootView = inflater.inflate(R.layout.fragment_search,container,false);
       initInstance(rootView);
        return rootView;
    }


    private void initInstance(View rootView) {

        searchRecycler = rootView.findViewById(R.id.searchRecycler);

        SearchAdapter.getInstance().init(getActivity().getBaseContext());

        searchRecycler.setAdapter(SearchAdapter.getInstance());
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

    }



    private static SearchFragment instance;

    public SearchFragment(){

    }



}
