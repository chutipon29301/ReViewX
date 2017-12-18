package com.chutipon.reviewx.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 18/12/2017 AD.
 */

public class MovieInfoDao {
    @SerializedName("genres")               private GenreObjectDao genres[];
    @SerializedName("id")                   private int id;
    @SerializedName("original_title")       private String originalTitle;
    @SerializedName("overview")             private String overview;
    @SerializedName("poster_path")          private String posterPath;
    @SerializedName("release_date")         private String releaseDate;
    @SerializedName("runtime")              private int runtime;
    @SerializedName("title")                private String title;
    @SerializedName("video")                private boolean video;
    @SerializedName("vote_average")         private double vote_average;

    public GenreObjectDao[] getGenres() {
        return genres;
    }

    public void setGenres(GenreObjectDao[] genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
