package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.adapter.MovieListAdapter;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView movieListRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;
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
        MovieListAdapter.getInstance().init(getActivity().getBaseContext());

        movieListRecycler.setAdapter(MovieListAdapter.getInstance());
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        btnwrite.setOnClickListener(this);

        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieListRecycler.setAdapter(MovieListAdapter.getInstance());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        showMainTutorial();
    }



    private static MovieListFragment instance;

    public MovieListFragment(){

    }


    @Override
    public void onClick(View view) {
        if(view==btnwrite){
//            HomeActivity.getInstance().redirect(SearchActivity.class);
            HomeActivity.getInstance().triggerSearch();
        }
    }

    public void showMainTutorial(){
        //for some reasons calling this from Tutorial doesn't work RIP
        final FancyShowCaseView showWelcome = new FancyShowCaseView.Builder(getActivity())
                .title("Welcome to ReviewX!")
                .showOnce("showWelcome")
                .build();
        final FancyShowCaseView showMovieList = new FancyShowCaseView.Builder(getActivity())
                .title("Here movies are suggested according to your preference")
                .showOnce("showMovieList")
                .build();
        final FancyShowCaseView showWrite = new FancyShowCaseView.Builder(getActivity())
                .focusOn(btnwrite)
                .title("You can write your review here")
                .showOnce("showWrite")
                .build();
        final FancyShowCaseView showMenu = new FancyShowCaseView.Builder(getActivity())
                .title("← You can open menu here")
                .titleSize(20, TypedValue.COMPLEX_UNIT_SP)
                .titleGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL)
                .showOnce("showMenu")
                .build();
        new FancyShowCaseQueue().add(showWelcome)
                .add(showMovieList)
                .add(showWrite)
                .add(showMenu).show();
    }
}
