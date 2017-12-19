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
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.chutipon.reviewx.manager.ReadLaterManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReadLaterAdapter extends RecyclerView.Adapter<ReadLaterAdapter.ViewHolder> implements View.OnClickListener, ReadLaterManager.onLoad {

    private LayoutInflater mInflater;

    private ReadLaterAdapter() {

    }

    public static ReadLaterAdapter getInstance() {
        if (instance == null) {
            instance = new ReadLaterAdapter();
        }
        return instance;
    }

    private static ReadLaterAdapter instance;

    public void init(Context cont) {
        mInflater = LayoutInflater.from(cont);
        Log.d("printming", mInflater + "");
        ReadLaterManager.getInstance().loadReadLaterMovieReviewList(this);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom, parent, false);
        parent.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadLaterAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        Log.d("itemcount", "" + ReadLaterManager.getInstance().getSize());
        return ReadLaterManager.getInstance().getSize();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLoadComplete() {
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MovieInfoManager.onLoad {

        private int position;
        private ImageView moviePic;
        private TextView score, reviewerName, firstword, secondword, thirdword;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initInstance(itemView);
        }

        private void initInstance(View itemView) {

            moviePic = itemView.findViewById(R.id.reviewerImg);
            reviewerName = itemView.findViewById(R.id.reviewerName);
            firstword = itemView.findViewById(R.id.firstWord);
            secondword = itemView.findViewById(R.id.secondWord);
            thirdword = itemView.findViewById(R.id.thirdWord);
            score = itemView.findViewById(R.id.score);
        }


        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReadReviewActivity.class, "reviewID", ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID());

        }

        public void bind(int position) {
            this.position = position;

            TextView[] words= {firstword,secondword,thirdword};
            for(int i=0;i<3;i++){
                words[i].setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));
            }

            reviewerName.setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookID());

            score.setText("Score: "+ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore());

        }

        @Override
        public void onLoadMovieInfo() {

            reviewerName.setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookID());

            score.setText("Score: " + ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore());
            TextView[] words = {firstword, secondword, thirdword};
            for (int i = 0; i < 3; i++) {
                words[i].setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));
            }
            reviewerName.setText(MovieInfoManager.getInstance().getMovieInfoDao().getTitle());
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(MovieInfoManager.getInstance().getMovieInfoDao().getPosterPath())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(transformation)
                    .into(moviePic);


        }

    }
}
