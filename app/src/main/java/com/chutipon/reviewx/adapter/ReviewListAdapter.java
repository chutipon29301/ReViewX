package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReadReviewActivity;
import com.chutipon.reviewx.manager.MovieReviewManager;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> implements View.OnClickListener, MovieReviewManager.onLoad {

    private LayoutInflater mInflater;

    private ReviewListAdapter() {

    }

    public static ReviewListAdapter getInstance() {
        if (instance == null) {
            instance = new ReviewListAdapter();
        }
        return instance;
    }

    private static ReviewListAdapter instance;

    public void init(Context cont, int movieID) {
        mInflater = LayoutInflater.from(cont);
        Log.d("printming", mInflater + "");
        MovieReviewManager.getInstance().getReview(movieID, this);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom, parent, false);
        parent.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return MovieReviewManager.getInstance().getSize();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLoadReviewComplete() {
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReadReviewActivity.class);
        }
    }
}
