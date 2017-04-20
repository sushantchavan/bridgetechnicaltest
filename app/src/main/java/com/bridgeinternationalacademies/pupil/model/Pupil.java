package com.bridgeinternationalacademies.pupil.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sushantchavan on 13/04/17.
 */

public class Pupil {
    //class attributes
    @SerializedName("pupilId")
    private String pupilId;
    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("latitude")
    private  float latitude;

    @SerializedName("longitude")
    private float longitude;

    //getter setter method for country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }


    public Pupil(String country, String name, String image, int latitude, int longitude) {
        this.country = country;
        this.name = name;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
