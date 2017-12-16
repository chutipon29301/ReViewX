package com.chutipon.reviewx.manager.http;

import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.chutipon.reviewx.dao.GenreListDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.chutipon.reviewx.dao.PreferenceInfoDao;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 9/12/2017 AD.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("/post/v1/addUser")
    Observable<GeneralResponseDao> addUser(@Field("facebookID") int facebookID, @Field("preference") PreferenceInfoDao preference);

    @POST("/post/v1/listGenre")
    Observable<GenreListDao> listGenre();

    @POST("/post/v1/listMovieSuggestion")
    Observable<MovieSuggestionListDao> listMovieSuggestion();


}
