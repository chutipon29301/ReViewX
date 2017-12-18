package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener, MovieInfoManager.onLoad {

    private static final String TAG = "WriteReviewActivity";
    private static WriteReviewActivity instance;
    private int movieID;
    EditText score;
    EditText firstWord;
    EditText secondWord;
    EditText thirdWord;
    Button reviewBtn;
    EditText questionEntry;
    ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writereview);

        Intent intent = getIntent();
        movieID = intent.getIntExtra("movieID",-1);
        Log.d(TAG, "onCreate: movieID " + movieID);
        if (movieID == -1){
            finish();
        }

        instance = this;
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        MovieInfoManager.getInstance().load(movieID,this);

        reviewBtn = findViewById(R.id.btn_review);
        score = findViewById(R.id.score);
        firstWord = findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
        thirdWord = findViewById(R.id.thirdWord);
        firstWord.setHorizontallyScrolling(true);
        secondWord.setHorizontallyScrolling(true);
        thirdWord.setHorizontallyScrolling(true);
        score.setMaxLines(1);
        movieImage = findViewById(R.id.movieImage);

        questionEntry = findViewById(R.id.reviewText);
        questionEntry.setScroller(new Scroller(getBaseContext()));
        questionEntry.setMaxLines(100);
        questionEntry.setVerticalScrollBarEnabled(true);
        questionEntry.setMovementMethod(new ScrollingMovementMethod());
        reviewBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == reviewBtn) {
            HomeActivity.getInstance().redirectToPage(HomeActivity.class);
        }

    }

    public static WriteReviewActivity getInstance() {
        return instance;
    }

    public void onLoadMovieInfo(){
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();
        Picasso.with(WriteReviewActivity.this)
                .load(MovieInfoManager.getInstance().getMovieInfoDao().getPosterPath())
                .resize(150, 150)
                .centerCrop()
                .transform(transformation)
                .into(movieImage);
    }

}
