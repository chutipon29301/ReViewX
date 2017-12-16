package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class PreferenceInfoDao {
    public void setLike(int[] like) {
        this.like = like;
    }

    @SerializedName("like")
    private int[] like;

    public void setDislike(int[] dislike) {
        this.dislike = dislike;
    }

    @SerializedName("dislike")
    private int[] dislike;

    public int[] getLike() {
        return like;
    }

    public int[] getDislike() {
        return dislike;
    }


}
