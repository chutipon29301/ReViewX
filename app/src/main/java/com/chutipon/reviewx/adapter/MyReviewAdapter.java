package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReadReviewActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.manager.AddReviewManager;
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.chutipon.reviewx.manager.MovieReviewManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import at.grabner.circleprogress.CircleProgressView;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> implements View.OnClickListener, MovieReviewManager.onLoad {

    private static final String TAG = "MyReviewAdapter";
    private LayoutInflater mInflater;

    private MyReviewAdapter() {

    }

    public static MyReviewAdapter getInstance() {
        if (instance == null) {
            instance = new MyReviewAdapter();
        }
        return instance;
    }

    private static MyReviewAdapter instance;

    public void init(Context cont) {
        mInflater = LayoutInflater.from(cont);
        Log.d("printming", mInflater + "");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom, parent, false);
        parent.setOnClickListener(this);
        MovieReviewManager.getInstance().getMyReview(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyReviewAdapter.ViewHolder holder, int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;
        private ImageView reviewerImg;
        CircleProgressView scoreBar;
        private TextView reviewerName, movieName;
        private TextView firstword, secondword, thirdword;
        private TextView score;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initinstance(itemView);
        }

        private void initinstance(View itemView) {
            movieName = itemView.findViewById(R.id.reviewerName);
            firstword = itemView.findViewById(R.id.firstWord);
            secondword = itemView.findViewById(R.id.secondWord);
            thirdword = itemView.findViewById(R.id.thirdWord);
            score = itemView.findViewById(R.id.score);
            scoreBar = itemView.findViewById(R.id.scorebar);
            reviewerImg = itemView.findViewById(R.id.reviewerImg);
        }


        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReadReviewActivity.class, "reviewID", MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID());
        }

        public void bind(int position) {
            this.position = position;

            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getMoviePic())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(transformation)
                    .into(reviewerImg);
            TextView[] words = {firstword, secondword, thirdword};
            for (int i = 0; i < 3; i++) {
                words[i].setMaxLines(1);
                words[i].setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));
            }
            scoreBar.setValueAnimated(0,(long)MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore(),1000);
            movieName.setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getMovieName());

        }
    }
}
