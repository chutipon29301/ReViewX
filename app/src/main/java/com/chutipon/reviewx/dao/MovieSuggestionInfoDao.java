package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 12/12/2017 AD.
 */

public class MovieSuggestionInfoDao extends RealmObject{
    @PrimaryKey @SerializedName("id")   private int id;
    @SerializedName("vote_average")     private double voteAverage;
    @SerializedName("title")            private String title;
    @SerializedName("poster_path")      private String posterPath;
    @SerializedName("release_date")     private String releaseDate;
    @SerializedName("genreName")        private RealmList<String> genreName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public RealmList<String> getGenreName() {
        return genreName;
    }

    public void setGenreName(RealmList<String> genreName) {
        this.genreName = genreName;
    }
}
