package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class PreferenceInfoDao {

    @SerializedName("like")         private ArrayList<Integer> like;
    @SerializedName("dislike")      private ArrayList<Integer> dislike;

    public ArrayList<Integer> getLike() {
        return like;
    }

    public void setLike(ArrayList<Integer> like) {
        this.like = like;
    }

    public ArrayList<Integer> getDislike() {
        return dislike;
    }

    public void setDislike(ArrayList<Integer> dislike) {
        this.dislike = dislike;
    }
}
