package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.fragment.MyReviewFragment;


/**
 * Created by admin on 12/9/2017 AD.
 */

    public class MyReviewAdapter extends RecyclerView.Adapter< MyReviewAdapter.ViewHolder> implements View.OnClickListener {

   private LayoutInflater mInflater;
    private MyReviewAdapter(){

    }
    public static MyReviewAdapter getInstance() {
        if(instance==null){
            instance = new MyReviewAdapter();
        }
        return instance;
    }

    private static MyReviewAdapter instance;

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
    public void onBindViewHolder(MyReviewAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onClick(View view) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            HomeActivity.getInstance().redirectFragment(MyReviewFragment.getInstance());
        }
    }
}
