package com.chutipon.reviewx.manager;

import android.util.Log;

import com.chutipon.reviewx.dao.LocationInfoDao;
import com.chutipon.reviewx.dao.LocationListDao;
import com.chutipon.reviewx.dao.MovieReviewInfoDao;
import com.chutipon.reviewx.dao.MovieReviewListDao;
import com.chutipon.reviewx.dao.MovieSuggestionInfoDao;
import com.chutipon.reviewx.dao.MovieSuggestionListDao;
import com.chutipon.reviewx.dao.PreferenceDao;


import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicha Rojsrikul on 15/12/2560.
 */

public class RealmManager {
    private static final String TAG = "RealmManager";
    private static RealmManager instance;
    private final ThreadLocal<Realm> localRealm = new ThreadLocal<>();
    private RealmResults realmResults;
    private PreferenceDao preferenceDao;
    private LocationInfoDao locationInfoDao;
    private MovieReviewInfoDao movieReviewInfoDao;
    private MovieSuggestionInfoDao movieSuggestionInfoDao;

    RealmManager(){}

    public synchronized static RealmManager getInstance(){
        if(instance == null){
            instance = new RealmManager();
        }
        return instance;
    }

    /*
    PreferenceDao stuff
     */

    public void storePreferenceDao(final PreferenceDao pd){
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(pd);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess(){
                Log.i(TAG, "PreferenceDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error){
                Log.e(TAG, "ERROR in storePreferenceDao()");
            }
        });
    }

    public void storePreferenceDao(int userID, String rank) {
        preferenceDao = new PreferenceDao();
        preferenceDao.setUserID(userID);
        preferenceDao.setRank(rank);
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(preferenceDao);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess(){
                Log.i(TAG, "PreferenceDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error){
                Log.e(TAG, "ERROR in storePreferenceDao()");
            }
        });
    }

    public PreferenceDao findPreferenceDao(final int userID){
        runTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                preferenceDao = realm.where(PreferenceDao.class).equalTo("userID", userID).findFirst();
            }
        });
        return preferenceDao;
    }

    /*
    LocationInfoDao stuff
     */

    public void storeLocationInfoDao(final LocationInfoDao lid){
        //TODO:Test
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(lid);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess(){
                Log.i(TAG, "LocationInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error){
                Log.e(TAG, "ERROR in storeLocationInfoDao()");
            }
        });
    }

    public void storeLocationInfoDao(String name, double latitude, double longitude, String locationID){
        locationInfoDao = new LocationInfoDao();
        locationInfoDao.setName(name);
        locationInfoDao.setLatitude(latitude);
        locationInfoDao.setLongitude(longitude);
        locationInfoDao.setLocationID(locationID);
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(locationInfoDao);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess(){
                Log.i(TAG, "LocationInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error){
                Log.e(TAG, "ERROR in storeLocationInfoDao()");
            }
        });
    }

    public void storeAllLocationInfoDao(LocationInfoDao[] lidArray){
        for(LocationInfoDao lid : lidArray){
            storeLocationInfoDao(lid);
        }
    }

    public void storeAllLocationInfoDao(LocationListDao locationListDao){
        for(LocationInfoDao lid : locationListDao.getLocations()){
            storeLocationInfoDao(lid);
        }
    }

    public LocationInfoDao findLocationInfoDao(final String locationID){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    locationInfoDao = realm.where(LocationInfoDao.class).equalTo("locationID",locationID).findFirst();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return locationInfoDao;
    }

    public LocationInfoDao[] findAllLocationInfoDao(){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmResults = realm.where(LocationInfoDao.class).findAll();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return (LocationInfoDao[])realmResults.toArray(new LocationInfoDao[realmResults.size()]);
    }

    /*
    MovieReviewInfoDao stuff
     */

    public void storeMovieReviewInfoDao(final MovieReviewInfoDao mrid){
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(mrid);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "MovieReviewInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "ERROR in MovieReviewInfoDao");
            }
        });
    }

    public void storeMovieReviewInfoDao(String facebookID, int movieID, RealmList<String> threeWords, String review, int score, String reviewID){
        movieReviewInfoDao = new MovieReviewInfoDao();
        movieReviewInfoDao.setFacebookID(facebookID);
        movieReviewInfoDao.setMovieID(movieID);
        movieReviewInfoDao.setThreeWords(threeWords);
        movieReviewInfoDao.setReview(review);
        movieReviewInfoDao.setScore(score);
        movieReviewInfoDao.setReviewID(reviewID);
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(movieReviewInfoDao);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "MovieReviewInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "ERROR in MovieReviewInfoDao");
            }
        });
    }

    public void storeAllMovieReviewInfoDao(MovieReviewInfoDao[] mridArray){
        for (MovieReviewInfoDao mrid : mridArray){
            storeMovieReviewInfoDao(mrid);
        }
    }

    public void storeAllMoviewReviewInfoDao(MovieReviewListDao mrld){
        for (MovieReviewInfoDao mrid : mrld.getMovieReviewInfoDao()){
            storeMovieReviewInfoDao(mrid);
        }
    }

    public MovieReviewInfoDao findMovieReviewInfoDao(final String reviewID){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    movieReviewInfoDao = realm.where(MovieReviewInfoDao.class).equalTo("reviewID",reviewID).findFirst();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return movieReviewInfoDao;
    }

    public MovieReviewInfoDao[] findAllMovieReviewInfoDao(){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmResults = realm.where(MovieReviewInfoDao.class).findAll();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return (MovieReviewInfoDao[])realmResults.toArray(new MovieReviewInfoDao[realmResults.size()]);
    }

    public void deleteMovieReviewInfoDao(final String reviewID){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    final RealmResults<MovieReviewInfoDao> results = realm.where(MovieReviewInfoDao.class).equalTo("reviewID",reviewID).findAll();
                    results.deleteAllFromRealm();
                }
            });
        }finally{
            closeLocalInstance();
        }
        Log.i(TAG, "A MovieReviewInfoDao is deleted from Realm Database");
    }

    /*
    MovieSuggestionInfoDao stuff
     */
    public void storeMovieSuggestionInfoDao(final MovieSuggestionInfoDao msid){
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(msid);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "MovieSuggestionInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "ERROR in MovieSuggestionInfoDao");
            }
        });
    }

    public void storeMovieSuggestionInfoDao(int id, double voteAverage, String title, String posterPath, String releaseDate, RealmList<String> genreName){
        movieSuggestionInfoDao = new MovieSuggestionInfoDao();
        movieSuggestionInfoDao.setId(id);
        movieSuggestionInfoDao.setVoteAverage(voteAverage);
        movieSuggestionInfoDao.setTitle(title);
        movieSuggestionInfoDao.setPosterPath(posterPath);
        movieSuggestionInfoDao.setReleaseDate(releaseDate);
        movieSuggestionInfoDao.setGenreName(genreName);
        runTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(movieSuggestionInfoDao);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "MovieReviewInfoDao is stored successfully.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "ERROR in MovieReviewInfoDao");
            }
        });
    }

    public void storeAllMovieSuggestionInfoDao(MovieSuggestionInfoDao[] msidArray){
        for (MovieSuggestionInfoDao msid : msidArray){
            storeMovieSuggestionInfoDao(msid);
        }
    }

    public void storeAllMoviewSuggestionInfoDao(MovieSuggestionListDao msld){
        for (MovieSuggestionInfoDao msid : msld.getMovieSuggestionInfoDao()){
            storeMovieSuggestionInfoDao(msid);
        }
    }

    public MovieSuggestionInfoDao findMovieSuggestionInfoDao(final int id){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    movieSuggestionInfoDao = realm.where(MovieSuggestionInfoDao.class).equalTo("id",id).findFirst();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return movieSuggestionInfoDao;
    }

    public MovieSuggestionInfoDao[] findAllMovieSuggestionInfoDao(){
        Realm realm = openLocalInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmResults = realm.where(MovieSuggestionInfoDao.class).findAll();
                }
            });
        }finally{
            closeLocalInstance();
        }
        return (MovieSuggestionInfoDao[])realmResults.toArray(new MovieSuggestionInfoDao[realmResults.size()]);
    }

    /**
     *  All hail EpicPandaForce on StackOverflow.
     *  My design might work, but it will have problems with Thread sooner or later.
     *  Better safe than sorry.
     */
    public Realm openLocalInstance() {
        Realm realm = Realm.getDefaultInstance();
        if(localRealm.get() == null) {
            localRealm.set(realm);
        }
        return realm;
    }

    public Realm getLocalInstance() {
        Realm realm = localRealm.get();
        if(realm == null) {
            throw new IllegalStateException("No open Realms were found on this thread.");
        }
        return realm;
    }

    public void closeLocalInstance() {
        Realm realm = localRealm.get();
        if(realm == null) {
            throw new IllegalStateException(
                    "Cannot close a Realm that is not open.");
        }
        realm.close();
        if(Realm.getLocalInstanceCount(Realm.getDefaultConfiguration()) <= 0) {
            localRealm.set(null);
        }
    }

    public final void runTransaction(Realm.Transaction transaction) {
        runTransaction(transaction, null, null);
    }

    public final void runTransaction(Realm.Transaction transaction, Realm.Transaction.OnSuccess onSuccess) {
        runTransaction(transaction, onSuccess, null);
    }

    public final void runTransaction(Realm.Transaction transaction, final Realm.Transaction.OnSuccess onSuccess, final Realm.Transaction.OnError onError) {
        Realm realm = openLocalInstance();
        if(realm.isAutoRefresh()) {
            realm.executeTransactionAsync(transaction, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    try {
                        if(onSuccess != null) {
                            onSuccess.onSuccess();
                        }
                    } finally {
                        closeLocalInstance();
                    }
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable e) {
                    try {
                        if(onError != null) {
                            onError.onError(e);
                        }
                    } finally {
                        closeLocalInstance();
                    }
                }
            });
        } else {
            try {
                realm.executeTransaction(transaction);
                if(onSuccess != null) {
                    onSuccess.onSuccess();
                }
            } catch(Exception e) {
                if(onError != null) {
                    onError.onError(e);
                }
                throw new RuntimeException(e);
            } finally {
                closeLocalInstance();
            }
        }
    }
}
