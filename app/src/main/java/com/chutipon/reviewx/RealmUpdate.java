package com.chutipon.reviewx;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Nicha Rojsrikul on 15/12/2560.
 */

public class RealmUpdate implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        //Use this for migration
    }
}
