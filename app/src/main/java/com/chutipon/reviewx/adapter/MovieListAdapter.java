package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.fragment.MovieListFragment;

import com.chutipon.reviewx.fragment.ReviewListFragment;

import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.MovieSuggestionManager;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG = "MovieListAdapter";
    private LayoutInflater mInflater;

    private MovieListAdapter() {

    }

    public static MovieListAdapter getInstance() {
        if (instance == null) {
            instance = new MovieListAdapter();
        }
        return instance;
    }

    private static MovieListAdapter instance;

    public void init(Context cont) {
        mInflater = LayoutInflater.from(cont);
        Log.d("printming", mInflater + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_movielist_custom, parent, false);
        parent.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return MovieSuggestionManager.getInstance().getSize();
    }

    @Override
    public void onClick(View view) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReviewListActivity.class, "movieID", MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getId());
//            HomeActivity.getInstance().redirectFragment(ReviewListFragment.getInstance());
        }
    }
}
