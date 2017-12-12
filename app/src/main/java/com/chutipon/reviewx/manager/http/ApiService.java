package com.chutipon.reviewx.manager.http;

import com.chutipon.reviewx.dao.GenreListDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by admin on 9/12/2017 AD.
 */

public interface ApiService {
    @POST("/post/v1/listGenre")
    Observable<GenreListDao> listGenre();

    @POST("/post/v1/listMovieSuggestion")
    Observable<MovieSuggestionListDao> listMovieSuggestion();
}