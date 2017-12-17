package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MovieReviewListDao {
    @SerializedName("reviews")      private MovieReviewInfoDao movieReviewInfoDao[];

    public MovieReviewInfoDao[] getMovieReviewInfoDao() {
        return movieReviewInfoDao;
    }

    public void setMovieReviewInfoDao(MovieReviewInfoDao[] movieReviewInfoDao) {
        this.movieReviewInfoDao = movieReviewInfoDao;
    }
}
