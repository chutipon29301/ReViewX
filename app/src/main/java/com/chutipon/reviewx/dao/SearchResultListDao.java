package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 17/12/2017 AD.
 */

public class SearchResultListDao {
    @SerializedName("searches")     private SearchResultInfoDao searchResultInfoDaos[];

    public SearchResultInfoDao[] getSearchResultInfoDaos() {
        return searchResultInfoDaos;
    }

    public void setSearchResultInfoDaos(SearchResultInfoDao[] searchResultInfoDaos) {
        this.searchResultInfoDaos = searchResultInfoDaos;
    }
}
