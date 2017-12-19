package com.chutipon.reviewx.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.dao.MovieReviewInfoDao;
import com.chutipon.reviewx.manager.MovieReviewManager;
import com.chutipon.reviewx.manager.ReadLaterManager;
import com.chutipon.reviewx.manager.ReviewInfoManager;
import com.chutipon.reviewx.util.Contextor;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ReadReviewActivity extends AppCompatActivity implements View.OnClickListener, MovieReviewManager.onLoad, ReviewInfoManager.onLoad, ReadLaterManager.onLoad, ReadLaterManager.onReadLater {

    private static final String TAG = "ReadReviewActivity";
    private static ReadReviewActivity instance;
    private boolean check;
    private String reviewId;
    private ImageButton bookmark;
    private TextView movieName;
    private TextView score, firstWord, secondWord, thirdWord, fullReview;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_readreview);
        Log.d(TAG, "onCreate: " + getIntent().getStringExtra("reviewID"));
        ReviewInfoManager.getInstance().load(getIntent().getStringExtra("reviewID"), this);
        initInstance(savedInstanceState);

        ReadLaterManager.getInstance().loadReadLaterMovieReviewList(this);
    }

    @SuppressLint("WrongViewCast")
    private void initInstance(Bundle savedInstanceState) {
        instance = this;
        bookmark = findViewById(R.id.btn_bookmark);
        bookmark.setOnClickListener(this);
        firstWord = findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
        thirdWord = findViewById(R.id.thirdWord);
        score = findViewById(R.id.score);
        movieName = findViewById(R.id.movieName);
        fullReview = findViewById(R.id.reviewText);
        img = findViewById(R.id.reviewerImg);
        reviewId = ReadReviewActivity.getInstance().getIntent().getStringExtra("reviewID");
        ReadLaterManager.getInstance().checkReadLater(reviewId, this);
    }

    @Override
    public void onClick(View view) {
        if (view == bookmark) {
            Log.d(TAG, "onClick: " + !check);
            if (!(check)) {
                bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_white_24dp));
                bookmark.setSelected(true);
                check = true;
                ReadLaterManager.getInstance().addReadLater(reviewId, this);

            } else {
                bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                bookmark.setSelected(false);
                check = false;
                ReadLaterManager.getInstance().deleteReadLater(reviewId, this);
            }
        }

    }

    public static ReadReviewActivity getInstance() {
        return instance;
    }

    @Override
    public void onLoadReviewComplete() {
    }

    @Override
    public void onReviewLoad(MovieReviewInfoDao movieReviewInfoDao) {
        fullReview.setText(movieReviewInfoDao.getReview());
        TextView[] words = {firstWord, secondWord, thirdWord};
        for (int i = 0; i < 3; i++) {
            words[i].setText(movieReviewInfoDao.getThreeWords().get(i));
        }
        score.setText("Score: "+movieReviewInfoDao.getScore());
        movieName.setText(""+movieReviewInfoDao.getMovieName());

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();
        Picasso.with(Contextor.getInstance().getContext())
                .load(movieReviewInfoDao.getFacebookPic())
                .resize(150, 150)
                .centerCrop()
                .transform(transformation)
                .into(img);
    }

    @Override
    public void onLoadComplete() {
    }

    @Override
    public void onCheckReadLaterResult(boolean inReadLater) {
        check = inReadLater;
        if (check) {
            bookmark.setSelected(true);
            bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_white_24dp));
        } else {
            bookmark.setSelected(false);
            bookmark.setImageDrawable(Contextor.getInstance().getContext().getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
        }
    }
}
