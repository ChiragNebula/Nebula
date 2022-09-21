package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 26-Feb-18.
 */

public class IBOListDetails {

    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("IBOUser")
    @Expose
    private String IBOUser;

    @SerializedName("DOJ")
    @Expose
    private long DOJ;

    @SerializedName("LevelNo")
    @Expose
    private Integer LevelNo;

    @SerializedName("Leg")
    @Expose
    private Integer Leg;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Rank")
    @Expose
    private String Rank;

    @SerializedName("GBV")
    @Expose
    private Integer GBV;

    @SerializedName("BVPercent")
    @Expose
    private double BVPercent;


    public IBOListDetails(String IBOID, String IBOUser, long DOJ, int LevelNo, Integer Leg, String Mobile, String Email, String Rank, Integer GBV, double BVPercent) {
        this.IBOID = IBOID;
        this.IBOUser = IBOUser;
        this.DOJ = DOJ;
        this.LevelNo = LevelNo;
        this.Leg = Leg;
        this.Mobile = Mobile;
        this.Email = Email;
        this.Rank = Rank;
        this.GBV = GBV;
        this.BVPercent = BVPercent;
    }

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getIBOUser() {
        return IBOUser;
    }

    public void setIBOUser(String IBOUser) {
        this.IBOUser = IBOUser;
    }

    public long getDOJ() {
        return DOJ;
    }

    public void setDOJ(long DOJ) {
        this.DOJ = DOJ;
    }

    public Integer getLevelNo() {
        return LevelNo;
    }

    public void setLevelNo(Integer levelNo) {
        LevelNo = levelNo;
    }

    public Integer getLeg() {
        return Leg;
    }

    public void setLeg(Integer leg) {
        Leg = leg;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public Integer getGBV() {
        return GBV;
    }

    public void setGBV(Integer GBV) {
        this.GBV = GBV;
    }

    public double getBVPercent() {
        return BVPercent;
    }

    public void setBVPercent(double BVPercent) {
        this.BVPercent = BVPercent;
    }
}
