package com.chutipon.reviewx.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.MainActivity;
import com.chutipon.reviewx.adapter.MovieListAdapter;
import com.chutipon.reviewx.dao.PreferenceDao;
import com.chutipon.reviewx.manager.PreferenceManager;
import com.chutipon.reviewx.manager.RealmManager;
import com.chutipon.reviewx.util.Contextor;

import io.realm.Realm;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListFragment extends Fragment {
    private RecyclerView movieListRecycler;
    private Realm realm;

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

        MovieListAdapter.getInstance().init(getActivity().getBaseContext());

        movieListRecycler.setAdapter(MovieListAdapter.getInstance());
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

    }



    private static MovieListFragment instance;

    public MovieListFragment(){

    }
}
