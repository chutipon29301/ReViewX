package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReadReviewActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.MovieSuggestionManager;
import com.chutipon.reviewx.manager.ReviewListManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import com.chutipon.reviewx.manager.MovieReviewManager;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> implements View.OnClickListener, MovieReviewManager.onLoad {

   private LayoutInflater mInflater;

    private ReviewListAdapter(){


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
        holder.bind(position);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MovieInfoManager.onLoad {
        private int position;
        private ImageView reviewerImg;

        private TextView reviewerName,movieName;
        private TextView firstword,secondword,thirdword;
        private TextView score;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initInstance(itemView);
        }

        private void initInstance(View itemView) {
            reviewerName = itemView.findViewById(R.id.reviewerName);
            firstword = itemView.findViewById(R.id.firstWord);
            secondword = itemView.findViewById(R.id.secondWord);
            thirdword = itemView.findViewById(R.id.thirdWord);
            score = itemView.findViewById(R.id.score);
            movieName =itemView.findViewById(R.id.movieName);
//            reviewerImg = itemView.findViewById(R.id.reviewerImg);

        }


        @Override
        public void onClick(View view) {
//            HomeActivity.getInstance().redirect(ReviewListActivity.class, "movieID", MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getId());
            ReviewListActivity.getInstance().redirect(ReadReviewActivity.class,"reviewID", MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID());
        }

        public void bind(int position) {
            this.position = position;
            MovieInfoManager.getInstance().load(ReviewListManager.getInstance().getMovieReviewInfoAtIndex(position).getMovieID(),this);
//            Transformation transformation = new RoundedTransformationBuilder()
//                    .cornerRadiusDp(30)
//                    .oval(true)
//                    .build();
//            Picasso.with(itemView.getContext())
//                    .load(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID().)
//                    .resize(150, 150)
//                    .centerCrop()
//                    .transform(transformation)
//                    .into(reviewerImg);
            TextView[] words= {firstword,secondword,thirdword};
            for(int i=0;i<3;i++){
                words[i].setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));
            }
            score.setText(String.valueOf(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore()));
            reviewerName.setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookID());
        }


        @Override
        public void onLoadMovieInfo() {
            movieName.setText(MovieInfoManager.getInstance().getMovieInfoDao().getTitle());
        }
    }
}
