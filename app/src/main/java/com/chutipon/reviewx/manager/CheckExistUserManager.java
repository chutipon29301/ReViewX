package com.chutipon.reviewx.manager;

/**
 * Created by admin on 14/12/2017 AD.
 */

public class CheckExistUserManager {
    private static CheckExistUserManager instance;
    private CheckExistUserManager(){
    }

    public static CheckExistUserManager getInstance(){
        if (instance == null){
            instance = new CheckExistUserManager();
        }
        return instance;
    }
}
