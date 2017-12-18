package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.fragment.MovieListFragment;

import com.chutipon.reviewx.fragment.ReviewListFragment;

import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.MovieSuggestionManager;
import com.chutipon.reviewx.manager.PreferenceManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


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
        private int position;
        private ImageView imageView;
        private TextView movieName, releaseDate,score;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            initInstance(itemView);
        }

        private void initInstance(View itemView) {
            imageView = itemView.findViewById(R.id.moviePic);
            movieName = itemView.findViewById(R.id.movieName);
            releaseDate = itemView.findViewById(R.id.releaseDate);
            score = itemView.findViewById(R.id.score);
        }

        void bind(int position) {
            this.position = position;
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getPosterPath())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(transformation)
                    .into(imageView);
            movieName.setText(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getTitle());
            releaseDate.setText(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getReleaseDate());
            score.setText(String.valueOf(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getVoteAverage()));
        }

        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReviewListActivity.class, "movieID", MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getId());
//            HomeActivity.getInstance().redirectFragment(ReviewListFragment.getInstance());
        }
    }
}
