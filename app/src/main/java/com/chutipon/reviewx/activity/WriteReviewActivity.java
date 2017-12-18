package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

import com.chutipon.reviewx.R;

public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static WriteReviewActivity instance;
    EditText score;
    EditText firstWord;
    EditText secondWord;
    EditText thirdWord;
    Button reviewBtn;
EditText questionEntry;
    private final String TAG = "ReadReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writereview);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        instance = this;
        reviewBtn = findViewById(R.id.btn_review);
        score = findViewById(R.id.score);
        firstWord= findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
        thirdWord = findViewById(R.id.thirdWord);
        firstWord.setHorizontallyScrolling(true);
        secondWord.setHorizontallyScrolling(true);
        thirdWord.setHorizontallyScrolling(true);
        score.setMaxLines(1);

        questionEntry = findViewById(R.id.reviewText);
        questionEntry.setScroller(new Scroller(getBaseContext()));
        questionEntry.setMaxLines(100);
        questionEntry.setVerticalScrollBarEnabled(true);
        questionEntry.setMovementMethod(new ScrollingMovementMethod());
        reviewBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==reviewBtn){
            HomeActivity.getInstance().redirectToPage(HomeActivity.class);
        }

    }

    public static WriteReviewActivity getInstance(){
        return instance;
    }

}
