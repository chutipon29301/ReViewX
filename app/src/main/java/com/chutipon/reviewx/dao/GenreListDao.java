package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 9/12/2017 AD.
 */

public class GenreListDao {
    @SerializedName("genres")       private GenreInfoDao genres[];

    public GenreInfoDao[] getGenres() {
        return genres;
    }

    public void setGenres(GenreInfoDao[] genres) {
        this.genres = genres;
    }
}
