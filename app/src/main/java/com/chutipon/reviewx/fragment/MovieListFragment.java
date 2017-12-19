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
import com.chutipon.reviewx.adapter.MovieListAdapter;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListFragment extends Fragment implements View.OnClickListener {
    private static MovieListFragment instance;
    private RecyclerView movieListRecycler;
    private FloatingActionButton btnwrite;

    public static MovieListFragment getInstance() {
        if (instance == null) {
            instance = new MovieListFragment();
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
        movieListRecycler = rootView.findViewById(R.id.movielistRecycler);
        btnwrite = rootView.findViewById(R.id.btn_write);
        MovieListAdapter.getInstance().init(getActivity().getBaseContext());

        movieListRecycler.setAdapter(MovieListAdapter.getInstance());
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        btnwrite.setOnClickListener(this);
        showMainTutorial();
    }

    @Override
    public void onClick(View view) {
        if (view == btnwrite) {
            HomeActivity.getInstance().triggerSearch();
        }
    }

    public void showMainTutorial() {
        //for some reasons calling this from Tutorial doesn't work RIP
        final FancyShowCaseView showWelcome = new FancyShowCaseView.Builder(getActivity())
                .title("Welcome to ReviewX!")
                .showOnce("showWelcome")
                .build();
        final FancyShowCaseView showWrite = new FancyShowCaseView.Builder(getActivity())
                .focusOn(btnwrite)
                .title("You can write your review here")
                .showOnce("showWrite")
                .build();
        //TODO: Add FancyShowCaseView for menu button
        final FancyShowCaseView showMenu = null;
        new FancyShowCaseQueue().add(showWelcome).add(showWrite).show();
    }
}
