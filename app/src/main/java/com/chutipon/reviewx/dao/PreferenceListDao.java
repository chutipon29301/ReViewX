package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class PreferenceListDao {

    @SerializedName("facebookID")        private String facebookId;
    @SerializedName("preference")        private PreferenceInfoDao preference;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public PreferenceInfoDao getPreference() {
        return preference;
    }

    public void setPreference(PreferenceInfoDao preference) {
        this.preference = preference;
    }
}
