package com.chutipon.reviewx.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.chutipon.reviewx.R;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class PreferenceAdapter extends BaseAdapter{
    ImageView imageView;
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view!=null){
            imageView = (ImageView)view ;
        }else{
            imageView = new ImageView(viewGroup.getContext());
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        }
        imageView.setImageResource(R.drawable.anime);
        return imageView;
    }
}
