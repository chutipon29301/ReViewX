package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReadReviewActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.MovieSuggestionManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;


/**
 * Created by admin on 12/9/2017 AD.
 */

    public class ReviewListAdapter extends RecyclerView.Adapter< ReviewListAdapter.ViewHolder> implements View.OnClickListener {

   private LayoutInflater mInflater;

    private ReviewListAdapter(){

    }
    public static ReviewListAdapter getInstance() {
        if(instance==null){
            instance = new ReviewListAdapter();
        }
        return instance;
    }

    private static ReviewListAdapter instance;

    public void init(Context cont){
        mInflater = LayoutInflater.from(cont);
        Log.d("printming", mInflater+"");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom,parent,false);
        parent.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return MovieReviewManager.getInstance().getSize();
    }

    @Override
    public void onClick(View view) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;
        private Target reviewerImg;
        private TextView movieName;
        private TextView firstword;
        private TextView secondword;
        private TextView thirdword;
        private TextView score;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initInstance(itemView);
        }

        private void initInstance(View itemView) {


        }


        @Override
        public void onClick(View view) {
//            HomeActivity.getInstance().redirect(ReviewListActivity.class, "movieID", MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getId());

        }

        public void bind(int position) {
//            this.position = position;
//            Transformation transformation = new RoundedTransformationBuilder()
//                    .cornerRadiusDp(30)
//                    .oval(true)
//                    .build();
//            Picasso.with(itemView.getContext())
//                    .load(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position))
//                    .resize(150, 150)
//                    .centerCrop()
//                    .transform(transformation)
//                    .into(reviewerImg);
//            movieName.setText(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getTitle());
//            firstword.setText(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getReleaseDate());
//            score.setText(String.valueOf(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getVoteAverage()));

        }


    }
}
