package com.chutipon.reviewx.manager.http;

import com.chutipon.reviewx.dao.AddReviewDao;
import com.chutipon.reviewx.dao.CheckExistUserDao;
import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.chutipon.reviewx.dao.GenreListDao;
import com.chutipon.reviewx.dao.LocationListDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.chutipon.reviewx.dao.PreferenceListDao;

import io.reactivex.Observable;
import retrofit2.http.Body;
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

    @POST("/post/v1/addUser")
    Observable<GeneralResponseDao> addUser(@Body PreferenceListDao preferenceListDao);


    @POST("/post/v1/listGenre")
    Observable<GenreListDao> listGenre();

    @POST("/post/v1/listMovieSuggestion")
    Observable<MovieSuggestionListDao> listMovieSuggestion();

    @POST("/post/v1/addReview")
    Observable<GeneralResponseDao> addReview(@Body AddReviewDao addReviewDao);

    @POST("/post/v1/listLocation")
    Observable<LocationListDao> getLocation();

}
