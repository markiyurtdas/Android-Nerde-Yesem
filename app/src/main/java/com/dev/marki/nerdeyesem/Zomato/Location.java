package com.dev.marki.nerdeyesem.Zomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("locality")
    @Expose
    public String locality;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("zipcode")
    @Expose
    public String zipcode;
    @SerializedName("country_id")
    @Expose
    public Integer countryId;
    @SerializedName("locality_verbose")
    @Expose
    public String localityVerbose;


    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }
}
