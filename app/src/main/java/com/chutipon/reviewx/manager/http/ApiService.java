package com.chutipon.reviewx.manager.http;

import com.chutipon.reviewx.dao.AddReviewDao;
import com.chutipon.reviewx.dao.CheckExistUserDao;
import com.chutipon.reviewx.dao.CheckReadLaterDao;
import com.chutipon.reviewx.dao.GeneralResponseDao;
import com.chutipon.reviewx.dao.GenreListDao;
import com.chutipon.reviewx.dao.LocationListDao;
import com.chutipon.reviewx.dao.MovieInfoDao;
import com.chutipon.reviewx.dao.MovieReviewInfoDao;
import com.chutipon.reviewx.dao.MovieReviewListDao;
import com.chutipon.reviewx.dao.MovieSuggestionInfoDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.chutipon.reviewx.dao.PreferenceListDao;
import com.chutipon.reviewx.dao.SearchResultListDao;

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

    @FormUrlEncoded
    @POST("/post/v1/listMovieSuggestion")
    Observable<MovieSuggestionListDao> listMovieSuggestion(@Field("userID") String userID);

    @POST("/post/v1/addReview")
    Observable<GeneralResponseDao> addReview(@Body AddReviewDao addReviewDao);

    @POST("/post/v1/listLocation")
    Observable<LocationListDao> getLocation();

    @FormUrlEncoded
    @POST("/post/v1/searchMovie")
    Observable<SearchResultListDao> searchMovie(@Field("key") String key);

    @FormUrlEncoded
    @POST("/post/v1/listReviewForMovie")
    Observable<MovieReviewListDao> getMovieReview(@Field("movieID") int movieID);

    @FormUrlEncoded
    @POST("/post/v1/getRandomMovie")
    Observable<MovieSuggestionInfoDao> getRandomMovie(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("/post/v1/getMovieInfo")
    Observable<MovieInfoDao> getMovieInfo(@Field("movieID") int movieID);

    @FormUrlEncoded
    @POST("/post/v1/addReadLater")
    Observable<GeneralResponseDao> addReadLater(@Field("userID") String userID, @Field("reviewID") String reviewID);

    @FormUrlEncoded
    @POST("/post/v1/deleteReadLater")
    Observable<GeneralResponseDao> deleteReadLater(@Field("userID") String userID, @Field("reviewID") String reviewID);

    @FormUrlEncoded
    @POST("/post/v1/listReadLaterReview")
    Observable<MovieReviewListDao> getReadLaterMovieReviewList(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("/post/v1/getReview")
    Observable<MovieReviewInfoDao> getReview(@Field("reviewID") String reviewID);

    @FormUrlEncoded
    @POST("/post/v1/listMyReview")
    Observable<MovieReviewListDao> getMyReview(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("/post/v1/isInReadLater")
    Observable<CheckReadLaterDao> checkReadLater(@Field("userID") String userID, @Field("reviewID") String reviewID);
}
