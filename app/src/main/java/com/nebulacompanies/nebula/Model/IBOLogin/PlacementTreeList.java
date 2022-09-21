package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 31-01-2018.
 */

public class PlacementTreeList {

    @SerializedName("LevelNo")
    @Expose
    private Integer LevelNo;

    @SerializedName("memberleg")
    @Expose
    private String IBOLeg;

    @SerializedName("MemberID")
    @Expose
    private String IBOID;

    @SerializedName("MemberName")
    @Expose
    private String IBOName;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Designation")
    @Expose
    private String Rank;

    @SerializedName("EntryDate")
    @Expose
    private String JoinDate;

    @SerializedName("DBV")
    @Expose
    private Integer GBV;

    @SerializedName("BVPercent")
    @Expose
    private double BVPercent;


    public PlacementTreeList(String IBOID, String IBOName, String Rank, int LevelNo, String IBOLeg, String Mobile, String Email, String JoinDate, Integer GBV, double BVPercent) {
        this.IBOID = IBOID;
        this.IBOName = IBOName;
        this.Rank = Rank;
        this.LevelNo = LevelNo;
        this.IBOLeg = IBOLeg;
        this.Mobile = Mobile;
        this.Email = Email;
        this.JoinDate = JoinDate;
        this.GBV = GBV;
        this.BVPercent = BVPercent;
    }



    public Integer getLevelNo() {
        return LevelNo;
    }

    public void setLevelNo(Integer levelNo) {
        LevelNo = levelNo;
    }

    public String getIBOLeg() {
        return IBOLeg;
    }

    public void setIBOLeg(String IBOLeg) {
        this.IBOLeg = IBOLeg;
    }

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

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
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
