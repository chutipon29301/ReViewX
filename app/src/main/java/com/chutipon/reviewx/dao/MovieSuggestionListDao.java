package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class MovieSuggestionListDao {
    @SerializedName("movieSuggestions")     private MovieSuggestionInfoDao movieSuggestionInfoDao[];

    public MovieSuggestionInfoDao[] getMovieSuggestionInfoDao() {
        return movieSuggestionInfoDao;
    }

    public void setMovieSuggestionInfoDao(MovieSuggestionInfoDao[] movieSuggestionInfoDao) {
        this.movieSuggestionInfoDao = movieSuggestionInfoDao;
    }
}
