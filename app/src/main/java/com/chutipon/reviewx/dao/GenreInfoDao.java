package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 9/12/2017 AD.
 */

public class GenreInfoDao {
    @SerializedName("genreID")      private int genreID;
    @SerializedName("name")         private String name;
    @SerializedName("image")        private String image;

    public int getGenreID() {return genreID;}

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
