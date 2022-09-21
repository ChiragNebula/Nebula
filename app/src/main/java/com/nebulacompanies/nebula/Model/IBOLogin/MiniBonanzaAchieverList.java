package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 22-Mar-18.
 */

public class MiniBonanzaAchieverList {

    /*@SerializedName("Count")
    @Expose
    private Integer Count;

    @SerializedName("LegPosition")
    @Expose
    private Integer LegPosition;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getLegPosition() {
        return LegPosition;
    }

    public void setLegPosition(Integer legPosition) {
        LegPosition = legPosition;
    }*/


    @SerializedName("IBOKeyID")
    @Expose
    private String IBOKeyID;

    @SerializedName("Achievers")
    @Expose
    private Integer Achievers;

    @SerializedName("Leg")
    @Expose
    private Integer Leg;

    public String getIBOKeyID() {
        return IBOKeyID;
    }

    public void setIBOKeyID(String IBOKeyID) {
        this.IBOKeyID = IBOKeyID;
    }

    public Integer getAchievers() {
        return Achievers;
    }

    public void setAchievers(Integer achievers) {
        Achievers = achievers;
    }

    public Integer getLeg() {
        return Leg;
    }

    public void setLeg(Integer leg) {
        Leg = leg;
    }
}
