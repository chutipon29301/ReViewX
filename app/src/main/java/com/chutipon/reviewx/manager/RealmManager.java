package com.chutipon.reviewx.manager;

import com.chutipon.reviewx.dao.PreferenceDao;


import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nicha Rojsrikul on 15/12/2560.
 */

public class RealmManager {
    private static RealmManager instance;
    private final ThreadLocal<Realm> localRealm = new ThreadLocal<>();
    private PreferenceDao preferenceDao;

    RealmManager(){}

    public synchronized static RealmManager getInstance(){
        if(instance == null){
            instance = new RealmManager();
        }
        return instance;
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
                System.out.println("Data is stored successfully!");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error){
                System.out.println("There is an error in storePreferenceDao()");
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

//    private static RealmManager instance;
//    private Realm realm;
//    private PreferenceDao preferenceDao;
//    private RealmResults<PreferenceDao> allPreferenceDao;
//
//
//    public static RealmManager getInstance() {
//        if( instance == null){
//            instance = new RealmManager();
//        }
//        return instance;
//    }
//
//    private RealmManager() {
//        realm = Realm.getDefaultInstance();
//    }
//
//    public Realm getRealm() { return realm; }
//
//    public void storePreferenceDao(final int userID, final String rank){
//        preferenceDao = new PreferenceDao();
//        preferenceDao.setUserID(userID);
//        preferenceDao.setRank(rank);
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(preferenceDao);
//            }
//        });
//    }
//
//    public PreferenceDao findPreferenceDao(final int userID){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                preferenceDao = realm.where(PreferenceDao.class).equalTo("userID", userID).findFirst();
//            }
//        });
//        return preferenceDao;
//    }
//
//    public RealmResults<PreferenceDao> findAllPreferenceDao(){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                allPreferenceDao = realm.where(PreferenceDao.class).findAll();
//            }
//        });
//        return allPreferenceDao;
//    }
//
//    public void deletePreferenceDao(final int userID){
//        preferenceDao = findPreferenceDao(userID);
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                preferenceDao.deleteFromRealm();
//            }
//        });
//    }
//
//    public void deleteAllPreferenceDao(){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                allPreferenceDao = findAllPreferenceDao();
//                allPreferenceDao.deleteAllFromRealm();
//            }
//        });
//    }
//
//    public void close(){
//        realm.close();
//        instance = null;
//    }
}
