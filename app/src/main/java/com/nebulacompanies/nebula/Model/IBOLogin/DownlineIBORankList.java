package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 28-02-2018.
 */

public class DownlineIBORankList {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("FullName")
    @Expose
    private String FullName;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("BVPercent")
    @Expose
    private float BVPercent;


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public float getBVPercent() {
        return BVPercent;
    }

    public void setBVPercent(float BVPercent) {
        this.BVPercent = BVPercent;
    }
}
