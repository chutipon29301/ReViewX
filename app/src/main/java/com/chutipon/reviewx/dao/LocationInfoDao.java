package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class LocationInfoDao extends RealmObject{
    @SerializedName("name")                     private String name;
    @SerializedName("latitude")                 private double latitude;
    @SerializedName("longitude")                private double longitude;
    @PrimaryKey @SerializedName("locationID")   private String locationID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
}
