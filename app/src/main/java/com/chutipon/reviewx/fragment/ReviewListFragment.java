package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
    Button btnMore;
    private ImageView movieImage;

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
        btnMore = rootView.findViewById(R.id.btn_more);
        ReviewListAdapter.getInstance().init(getActivity().getBaseContext());

        reviewListRecycler.setAdapter(ReviewListAdapter.getInstance());
        btnMore.setOnClickListener(this);
        reviewListRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        movieImage = rootView.findViewById(R.id.movieImage);
    }



    private static ReviewListFragment instance;

    public ReviewListFragment(){
    }


    @Override
    public void onClick(View view) {
        if(view== btnMore){

        }
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
    }
}
