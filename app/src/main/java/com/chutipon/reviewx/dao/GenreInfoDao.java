package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 9/12/2017 AD.
 */

public class GenreInfoDao {
    @SerializedName("genreID")      private int genreID;
    @SerializedName("name")         private String genreName;
    @SerializedName("image")        private String image;

    public int getGenreID() {return genreID;}

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}