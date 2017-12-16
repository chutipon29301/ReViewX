package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class PreferenceListDao {



    @SerializedName("facebookID")        private int facebookId;
    @SerializedName("preference")        private PreferenceInfoDao preference;

    public int getFacebookId() {
        return facebookId;
    }

    public PreferenceInfoDao getPreference() {
        return preference;
    }

    public void setFacebookId(int facebookId) {
        this.facebookId = facebookId;
    }

    public void setPreference(PreferenceInfoDao preference) {
        this.preference = preference;
    }

}
