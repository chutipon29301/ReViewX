package com.chutipon.reviewx.manager.http;

import com.chutipon.reviewx.dao.CheckExistUserDao;
import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.chutipon.reviewx.dao.GenreListDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.chutipon.reviewx.dao.PreferenceDao;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 9/12/2017 AD.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("/post/v1/checkExistUser")
    Observable<CheckExistUserDao> checkExistUser(@Field("facebookID") String facebookID);

    @FormUrlEncoded
    @POST("/post/v1/addUser")
    Observable<GeneralResponseDao> addUser(@Field("facebookID") int facebookID, @Field("preference") PreferenceDao preference);

    @POST("/post/v1/listGenre")
    Observable<GenreListDao> listGenre();

    @POST("/post/v1/listMovieSuggestion")
    Observable<MovieSuggestionListDao> listMovieSuggestion();

    @FormUrlEncoded
    @POST("/post/v1/addReview")
    Observable<GeneralResponseDao> addReview();

}
