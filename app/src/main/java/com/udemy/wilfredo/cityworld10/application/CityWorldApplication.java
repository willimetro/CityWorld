package com.udemy.wilfredo.cityworld10.application;

import android.app.Application;

import com.udemy.wilfredo.cityworld10.model.City;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Clase que sirve para la configuración de la base de datos
 * Created by wilfredo on 06-02-18.
 */

public class CityWorldApplication extends Application {

    public static AtomicInteger CITY_ID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();

        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        try {
            CITY_ID = getIdAnyTable(realm, City.class);
        } finally {
            realm.close();
        }
    }

    private <T extends RealmObject> AtomicInteger getIdAnyTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0)? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
