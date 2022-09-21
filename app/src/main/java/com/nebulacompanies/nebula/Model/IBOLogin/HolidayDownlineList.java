package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 29-01-2018.
 */

public class HolidayDownlineList {

    @SerializedName("MainLeg")
    @Expose
    private Integer MainLeg;

    @SerializedName("WeakerLeg")
    @Expose
    private Integer WeakerLeg;

    @SerializedName("WeakestLeg")
    @Expose
    private Integer WeakestLeg;

    @SerializedName("PrizesAmount")
    @Expose
    private Integer PrizesAmount;

    @SerializedName("MainLegMember")
    @Expose
    private String MainLegMember;

    @SerializedName("WeakerLegMember")
    @Expose
    private String WeakerLegMember;

    public Integer getMainLeg() {
        return MainLeg;
    }

    public void setMainLeg(Integer mainLeg) {
        MainLeg = mainLeg;
    }

    public Integer getWeakerLeg() {
        return WeakerLeg;
    }

    public void setWeakerLeg(Integer weakerLeg) {
        WeakerLeg = weakerLeg;
    }

    public Integer getWeakestLeg() {
        return WeakestLeg;
    }

    public void setWeakestLeg(Integer weakestLeg) {
        WeakestLeg = weakestLeg;
    }

    public Integer getPrizesAmount() {
        return PrizesAmount;
    }

    public void setPrizesAmount(Integer prizesAmount) {
        PrizesAmount = prizesAmount;
    }

    public String getMainLegMember() {
        return MainLegMember;
    }

    public void setMainLegMember(String mainLegMember) {
        MainLegMember = mainLegMember;
    }

    public String getWeakerLegMember() {
        return WeakerLegMember;
    }

    public void setWeakerLegMember(String weakerLegMember) {
        WeakerLegMember = weakerLegMember;
    }
}
