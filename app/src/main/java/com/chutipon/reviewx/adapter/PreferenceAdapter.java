package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;


import android.view.ViewGroup;

import com.chutipon.reviewx.R;

/**
 * Created by admin on 12/9/2017 AD.
 */


public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.ViewHolder>{

    private static PreferenceAdapter instance;
    private LayoutInflater mInflater;

    private PreferenceAdapter(){
        
    }

    public static PreferenceAdapter getInstance(){
        if (instance == null){
            instance = new PreferenceAdapter();
        }
        return instance;
    }

    public void init(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_genre_custom,parent,false);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {


        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
