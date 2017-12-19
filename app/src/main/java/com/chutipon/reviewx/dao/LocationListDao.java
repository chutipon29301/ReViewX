package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class LocationListDao {
    @SerializedName("locations")    private LocationInfoDao[] locations;

    public LocationInfoDao[] getLocations() {
        return locations;
    }

    public void setLocations(LocationInfoDao[] locations) {
        this.locations = locations;
    }
}
