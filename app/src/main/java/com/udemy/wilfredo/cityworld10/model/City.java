package com.udemy.wilfredo.cityworld10.model;

import com.udemy.wilfredo.cityworld10.application.CityWorldApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Clase que representa a una ciudad en la aplicación
 * Created by wilfredo on 06-02-18.
 */

public class City extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String name;
    @Required
    private String desc;
    @Required
    private String urlImgBackGround;
    private float starsPoints;

    public City(int id, String name, String desc, String urlImgBackGround, float starsPoints) {
        this.id = CityWorldApplication.CITY_ID.incrementAndGet();
        this.name = name;
        this.desc = desc;
        this.urlImgBackGround = urlImgBackGround;
        this.starsPoints = starsPoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlImgBackGround() {
        return urlImgBackGround;
    }

    public void setUrlImgBackGround(String urlImgBackGround) {
        this.urlImgBackGround = urlImgBackGround;
    }

    public float getStarsPoints() {
        return starsPoints;
    }

    public void setStarsPoints(float starsPoints) {
        this.starsPoints = starsPoints;
    }
}
