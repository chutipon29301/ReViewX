package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class AddReviewDao {
    @SerializedName("facebookID")       private String facebookID;
    @SerializedName("movieID")          private int movieID;
    @SerializedName("threeWords")       private ArrayList<String> threeWords;
    @SerializedName("review")           private String review;
    @SerializedName("score")            private int score;

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public ArrayList<String> getThreeWords() {
        return threeWords;
    }

    public void setThreeWords(ArrayList<String> threeWords) {
        this.threeWords = threeWords;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
