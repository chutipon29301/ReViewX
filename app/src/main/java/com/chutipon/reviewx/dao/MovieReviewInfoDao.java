package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MovieReviewInfoDao extends RealmObject{
    @SerializedName("facebookID")           private String facebookID;
    @SerializedName("movieID")              private int movieID;
    @SerializedName("movieName")              private String movieName;
    @SerializedName("moviePic")              private String moviePic;
    @SerializedName("facebookPic")          private String facebookPic;
    @SerializedName("facebookName")         private String facebookName;

    @SerializedName("threeWords")           private RealmList<String> threeWords;
    @SerializedName("review")               private String review;
    @SerializedName("score")                private int score;
    @PrimaryKey @SerializedName("reviewID") private String reviewID;

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

    public RealmList<String> getThreeWords() {
        return threeWords;
    }

    public void setThreeWords(RealmList<String> threeWords) {
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

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getFacebookPic() {
        return facebookPic;
    }

    public void setFacebookPic(String facebookPic) {
        this.facebookPic = facebookPic;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePic() {
        return moviePic;
    }

    public void setMoviePic(String moviePic) {
        this.moviePic = moviePic;
    }
}
