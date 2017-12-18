package com.chutipon.reviewx.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.MyReviewAdapter;
import com.chutipon.reviewx.adapter.PreferenceAdapter;
import com.chutipon.reviewx.util.Contextor;

public class ReadReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static ReadReviewActivity instance;
    ImageButton bookmark;
    private final String TAG = "ReadReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_readreview);
        initInstance(savedInstanceState);
    }

    @SuppressLint("WrongViewCast")
    private void initInstance(Bundle savedInstanceState) {
        instance = this;
        bookmark = findViewById(R.id.btn_bookmark);
        bookmark.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view==bookmark){
            if(!bookmark.isSelected()) {
                bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_white_24dp));
                bookmark.setSelected(true);
            }else{
                bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                bookmark.setSelected(false);
            }
        }

    }

    public static ReadReviewActivity getInstance(){
        return instance;
    }

}
