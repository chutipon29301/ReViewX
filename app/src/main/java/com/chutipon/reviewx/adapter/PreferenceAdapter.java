package com.chutipon.reviewx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;


import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.dao.PreferenceInfoDao;
import com.chutipon.reviewx.dao.PreferenceListDao;
import com.chutipon.reviewx.manager.PreferenceManager;
import com.chutipon.reviewx.util.Contextor;
import com.chutipon.reviewx.util.PreferenceUtil;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 12/9/2017 AD.
 */


public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.ViewHolder> {

    private static PreferenceAdapter instance;
    private static PreferenceInfoDao preferenceInfoDao;
    private LayoutInflater mInflater;
    private final String TAG = "PreferenceAdapter";

    private PreferenceAdapter() {

    }

    public static PreferenceAdapter getInstance() {
        if (instance == null) {
            instance = new PreferenceAdapter();
        }
        return instance;
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_genre_custom, parent, false);

        return new ViewHolder(view);

    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {

        if (PreferenceManager.getInstance().getGenreListDao() == null) {
            return 0;
        }
        if (PreferenceManager.getInstance().getGenreListDao().getGenres() == null) {
            return 0;
        }
        return PreferenceManager.getInstance().getGenreListDao().getGenres().length;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageButton img;
        int position;
        private TextView txtGenre;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.genrePic);
            txtGenre = itemView.findViewById(R.id.txt_genre);
            img.setOnClickListener(this);
        }

        void bind(int position) {
            this.position = position;
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .oval(true)
                    .build();
            Picasso.with(itemView.getContext())
                    .load(PreferenceManager.getInstance().getGenreListDao().getGenres()[position].getImage())
                    .resize(350, 350)
                    .centerCrop()
                    .transform(transformation)
                    .into(img);
            txtGenre.setText(PreferenceManager.getInstance().getGenreListDao().getGenres()[position].getName());
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {

            img.setSelected(!img.isSelected());

            if (img.isSelected()) {
                //Handle selected state change
//                PreferenceUtil.getInstance().addLike(PreferenceManager.getInstance().getGenreListDao().getGenres()[position].getGenreID());
                PreferenceUtil.getInstance().setValue(position, true);
                img.setBackground(Contextor.getInstance().getContext().getDrawable(R.drawable.circle_background));
            } else {
//                PreferenceUtil.getInstance().addDislike(PreferenceManager.getInstance().getGenreListDao().getGenres()[position].getGenreID());
                PreferenceUtil.getInstance().setValue(position, false);
                img.setBackgroundColor(Color.TRANSPARENT);
            }
        }

    }
}
