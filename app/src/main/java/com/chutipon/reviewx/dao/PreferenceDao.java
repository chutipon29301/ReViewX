package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class PreferenceDao extends RealmObject{
    @PrimaryKey @SerializedName("userID") private int userID;
    @SerializedName("rank") private String rank;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) { this.userID = userID; }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) { this.rank = rank; }
}
