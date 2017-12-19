package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.Scroller;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.activity.ReviewListActivity;
import com.chutipon.reviewx.fragment.MovieListFragment;

import com.chutipon.reviewx.fragment.ReviewListFragment;

import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.MovieSuggestionManager;
import com.chutipon.reviewx.manager.PreferenceManager;
import com.chutipon.reviewx.util.Contextor;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import at.grabner.circleprogress.CircleProgressView;
import at.markushi.ui.CircleButton;


/**
 * Created by admin on 12/9/2017 AD.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> implements MovieSuggestionManager.onLoad {

    private static final String TAG = "MovieListAdapter";
    private static MovieListAdapter instance;
    private LayoutInflater mInflater;

    private MovieListAdapter() {
    }

    public static MovieListAdapter getInstance() {
        if (instance == null) {
            instance = new MovieListAdapter();
        }
        return instance;
    }

    public void init(Context cont) {
        mInflater = LayoutInflater.from(cont);
        MovieSuggestionManager.getInstance().load(this);
        Log.d("printming", mInflater + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_movielist_custom, parent, false);
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
    public void onloadComplete() {
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;
        private ImageView imageView;
        private TextView movieName, releaseDate;
        CircleProgressView scoreBar;
        TextView genre;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            initInstance(itemView);
        }

        private void initInstance(View itemView) {
            imageView = itemView.findViewById(R.id.moviePic);
            movieName = itemView.findViewById(R.id.movieName);
            releaseDate = itemView.findViewById(R.id.releaseDate);
            scoreBar = itemView.findViewById(R.id.scorebar);
            genre = itemView.findViewById(R.id.genreDes);
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
            scoreBar.setValueAnimated(0, (long) (MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getVoteAverage()*10),1000);
            scoreBar.setText(String.valueOf(MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getVoteAverage()));
            String genrestr = "";
            for(int i=0;i<MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getGenreName().size();i++){
                genrestr+= MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getGenreName().get(i)+" ";
            }
            genre.setText(genrestr);

            genre.setScroller(new Scroller(Contextor.getInstance().getContext()));
            genre.setHorizontallyScrolling(true);
            genre.setMovementMethod(new ScrollingMovementMethod());
        }

        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirect(ReviewListActivity.class, "movieID", MovieSuggestionManager.getInstance().getMovieSuggestionInfoAtIndex(position).getId());
        }
    }
}
