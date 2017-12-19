package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/19/2017 AD.
 */

public class CheckReadLaterDao {
    @SerializedName("isReadLater")  private boolean readLater;

    public boolean isReadLater() {
        return readLater;
    }

    public void setReadLater(boolean readLater) {
        this.readLater = readLater;
    }
}
