package com.chutipon.reviewx.manager;

import com.chutipon.reviewx.dao.PreferenceDao;

import java.util.AbstractList;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nicha Rojsrikul on 15/12/2560.
 */

public class RealmManager {
    private static RealmManager instance;
    private Realm realm;
    private PreferenceDao preferenceDao;
    private RealmResults<PreferenceDao> allPreferenceDao;


    public static RealmManager getInstance() {
        if( instance == null){
            instance = new RealmManager();
        }
        return instance;
    }

    private RealmManager() {
        realm = Realm.getDefaultInstance();
    }

    public void storePreferenceDao(final int userID, final String rank){
        realm.beginTransaction();
        PreferenceDao preferenceDao = new PreferenceDao();
        preferenceDao.setUserID(userID);
        preferenceDao.setRank(rank);
        realm.copyToRealmOrUpdate(preferenceDao); //Can store new Preference or update old Preference
        realm.commitTransaction();
    }

    public PreferenceDao findPreferenceDao(final int userID){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                preferenceDao = realm.where(PreferenceDao.class).equalTo("userID", userID).findFirst();
            }
        });
        return preferenceDao;
    }

    public RealmResults<PreferenceDao> findAllPreferenceDao(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                allPreferenceDao = realm.where(PreferenceDao.class).findAll();
            }
        });
        return allPreferenceDao;
    }

    public void deleteAllPreferenceDao(){
        allPreferenceDao = findAllPreferenceDao();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                allPreferenceDao.deleteAllFromRealm();
            }
        });
    }
}
