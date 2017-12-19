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
import com.chutipon.reviewx.dao.AddReviewDao;
import com.chutipon.reviewx.manager.AddReviewManager;
import com.chutipon.reviewx.manager.HttpManager;
import com.chutipon.reviewx.manager.MovieInfoManager;
import com.facebook.AccessToken;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;


public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener, MovieInfoManager.onLoad, AddReviewManager.onLoadComplete {

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
        movieID = intent.getIntExtra("movieID", -1);
        Log.d(TAG, "onCreate: movieID " + movieID);
        if (movieID == -1) {
            finish();
        }

        instance = this;
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        MovieInfoManager.getInstance().load(movieID, this);

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
            AddReviewDao addReviewDao = new AddReviewDao();
            addReviewDao.setFacebookID(AccessToken.getCurrentAccessToken().getUserId());
            addReviewDao.setMovieID(movieID);
            ArrayList<String> threeWords = new ArrayList<>();
            threeWords.add(firstWord.getText().toString());
            threeWords.add(secondWord.getText().toString());
            threeWords.add(thirdWord.getText().toString());
            addReviewDao.setThreeWords(threeWords);
            addReviewDao.setReview(questionEntry.getText().toString());
            addReviewDao.setScore(Integer.parseInt(score.getText().toString()));
            AddReviewManager.getInstance().addReview(addReviewDao, this);
        }

    }

    public static WriteReviewActivity getInstance() {
        return instance;
    }

    @Override
    public void onLoadMovieInfo() {
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

    @Override
    public void onLoadComplete() {
        HomeActivity.getInstance().redirectToPage(HomeActivity.class);
    }
}
