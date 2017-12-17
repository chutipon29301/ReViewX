package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class LocationListDao extends RealmObject {
    @SerializedName("locations")    private RealmList<LocationInfoDao> locations;

    public RealmList<LocationInfoDao> getLocations() {
        return locations;
    }

    public void setLocations(RealmList<LocationInfoDao> locations) {
        this.locations = locations;
    }
}
