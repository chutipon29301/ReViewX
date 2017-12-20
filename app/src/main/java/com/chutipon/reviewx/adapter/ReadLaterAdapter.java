package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import at.grabner.circleprogress.CircleProgressView;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReadLaterAdapter extends RecyclerView.Adapter<ReadLaterAdapter.ViewHolder> implements ReadLaterManager.onLoad {

    private static final String TAG = "ReadLaterAdapter";
    private static ReadLaterAdapter instance;
    private LayoutInflater mInflater;
    private ReadLaterManager.onLoad callback;

    public interface onLoad {
        void onLoadComplete();
    }

    private ReadLaterAdapter() {
    }

    public static ReadLaterAdapter getInstance() {
        if (instance == null) {
            instance = new ReadLaterAdapter();
        }
        return instance;
    }

    public void init(Context cont, ReadLaterManager.onLoad callback) {
        mInflater = LayoutInflater.from(cont);
        this.callback = callback;
        ReadLaterManager.getInstance().loadReadLaterMovieReviewList(this);
    }

    public void refresh(ReadLaterManager.onLoad callback) {
        this.callback = callback;
        ReadLaterManager.getInstance().loadReadLaterMovieReviewList(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadLaterAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ReadLaterManager.getInstance().getSize();
    }

    @Override
    public void onLoadComplete() {
        notifyDataSetChanged();
        callback.onLoadComplete();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MovieInfoManager.onLoad {

        private int position;
        private ImageView moviePic;
        private TextView reviewerName;
        private TextView firstWord;
        private TextView secondWord;
        private TextView thirdWord;
        private CircleProgressView scoreBar;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initInstance(itemView);
        }

        private void initInstance(View itemView) {
            moviePic = itemView.findViewById(R.id.reviewerImg);
            reviewerName = itemView.findViewById(R.id.reviewerName);
            firstWord = itemView.findViewById(R.id.firstWord);
            secondWord = itemView.findViewById(R.id.secondWord);
            thirdWord = itemView.findViewById(R.id.thirdWord);
            scoreBar = itemView.findViewById(R.id.scorebar);
        }

        void bind(int position) {
            this.position = position;
            reviewerName.setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookName());
            TextView[] words = {firstWord, secondWord, thirdWord};
            for (int i = 0; i < 3; i++) {
                words[i].setText(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));
            }
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getMoviePic())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(transformation)
                    .into(moviePic);
            scoreBar.setValueAnimated(0, ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore(), 1000);
        }

        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReadReviewActivity.class, "reviewID", ReadLaterManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID());
        }

        @Override
        public void onLoadMovieInfo() {
        }
    }
}
