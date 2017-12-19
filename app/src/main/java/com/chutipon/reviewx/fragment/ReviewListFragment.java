package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.ReviewListAdapter;
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReviewListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView reviewListRecycler;
    private ImageView movieImage;

    TextView movieName;
    TextView releaseDate;
    TextView description;
    TextView runtime;

    public static ReviewListFragment getInstance() {
        if(instance==null){
            instance = new ReviewListFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_reviewlist,container,false);
       initInstance(rootView);
        return rootView;
    }


    private void initInstance(View rootView) {

        reviewListRecycler = rootView.findViewById(R.id.reviewlistRecycler);
        ReviewListAdapter.getInstance().init(getActivity().getBaseContext());

        reviewListRecycler.setAdapter(ReviewListAdapter.getInstance());

        reviewListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        movieImage = rootView.findViewById(R.id.movieImage);
        movieName = rootView.findViewById(R.id.movieName);
        description = rootView.findViewById(R.id.description);
        runtime = rootView.findViewById(R.id.runtime);
        releaseDate = rootView.findViewById(R.id.releaseDate);

        description.setScroller(new Scroller(getContext()));
        description.setMaxLines(5);
        description.setVerticalScrollBarEnabled(true);
        description.setMovementMethod(new ScrollingMovementMethod());

    }



    private static ReviewListFragment instance;

    public ReviewListFragment(){
    }


    @Override
    public void onClick(View view) {

    }

    public void onLoadMovieInfo(){
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();
        Picasso.with(getContext())
                .load(MovieInfoManager.getInstance().getMovieInfoDao().getPosterPath())
                .resize(150, 150)
                .centerCrop()
                .transform(transformation)
                .into(movieImage);
        movieName.setText(MovieInfoManager.getInstance().getMovieInfoDao().getTitle());
        description.setText("Description: "+MovieInfoManager.getInstance().getMovieInfoDao().getOverview());
        runtime.setText("Runtime: "+MovieInfoManager.getInstance().getMovieInfoDao().getRuntime());
        releaseDate.setText("Release Date: "+MovieInfoManager.getInstance().getMovieInfoDao().getReleaseDate());
    }
}
