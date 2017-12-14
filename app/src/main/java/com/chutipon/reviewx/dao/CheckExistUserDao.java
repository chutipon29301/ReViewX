package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 14/12/2017 AD.
 */

public class CheckExistUserDao {
    @SerializedName("exist") private boolean exist;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
