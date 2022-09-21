package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 25-01-2018.
 */

public class SuperBoosterIncomeList {


    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("IBOName")
    @Expose
    private String IBOName;

    @SerializedName("SponsorId")
    @Expose
    private String SponsorID;

    @SerializedName("Rank")
    @Expose
    private String Rank;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("State")
    @Expose
    private String State;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Percent")
    @Expose
    private Integer Percent;

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getIBOName() {
        return IBOName;
    }

    public void setIBOName(String IBOName) {
        this.IBOName = IBOName;
    }

    public String getSponsorID() {
        return SponsorID;
    }

    public void setSponsorID(String sponsorID) {
        SponsorID = sponsorID;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Integer getPercent() {
        return Percent;
    }

    public void setPercent(Integer percent) {
        Percent = percent;
    }
}
