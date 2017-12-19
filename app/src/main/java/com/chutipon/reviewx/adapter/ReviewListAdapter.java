package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.ReadReviewActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.manager.MovieReviewManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import at.grabner.circleprogress.CircleProgressView;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> implements MovieReviewManager.onLoad {

    private static final String TAG = "ReviewListAdapter";
    private static ReviewListAdapter instance;
    private LayoutInflater mInflater;

    private ReviewListAdapter() {
    }

    public static ReviewListAdapter getInstance() {
        if (instance == null) {
            instance = new ReviewListAdapter();
        }
        return instance;
    }


    public void init(Context cont, int movieID) {
        mInflater = LayoutInflater.from(cont);
        MovieReviewManager.getInstance().getReview(movieID, this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_reviewlist_custom, parent, false);
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
    public void onLoadReviewComplete() {
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;
        private ImageView reviewerImg;
        private CircleProgressView scoreBar;
        private TextView reviewerName;
        private TextView firstWord, secondWord, thirdWord;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initInstance(itemView);

        }

        private void initInstance(View itemView) {
            reviewerName = itemView.findViewById(R.id.reviewerName);
            firstWord = itemView.findViewById(R.id.firstWord);
            secondWord = itemView.findViewById(R.id.secondWord);
            thirdWord = itemView.findViewById(R.id.thirdWord);
            scoreBar = itemView.findViewById(R.id.scorebar);
            reviewerImg = itemView.findViewById(R.id.reviewerImg);

        }

        @Override
        public void onClick(View view) {
            ReviewListActivity.getInstance().redirect(ReadReviewActivity.class, "reviewID", MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getReviewID());
        }

        void bind(int position) {
            this.position = position;
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookPic())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(transformation)
                    .into(reviewerImg);
            TextView[] words = {firstWord, secondWord, thirdWord};
            for (int i = 0; i < 3; i++) {
                words[i].setMaxLines(1);

                words[i].setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getThreeWords().get(i));

            }
            scoreBar.setValueAnimated(0, (long) MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getScore(), 1000);
            reviewerName.setText(MovieReviewManager.getInstance().getMovieReviewInfoDaoAtIndex(position).getFacebookName());
        }
    }
}
