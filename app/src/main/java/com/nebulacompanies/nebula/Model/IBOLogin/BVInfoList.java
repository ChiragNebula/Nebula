package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 14-Feb-18.
 */

public class BVInfoList {

    @SerializedName("Level")
    @Expose
    private Integer Level;
    @SerializedName("IBOID")
    @Expose
    private String IBOID;
    @SerializedName("IBOName")
    @Expose
    private String IBOName;
    @SerializedName("DownlineMembers")
    @Expose
    private Integer DownlineMembers;
    @SerializedName("GBV")
    @Expose
    private Double GBV;
    @SerializedName("Leg")
    @Expose
    private Integer Leg;

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
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

    public Integer getDownlineMembers() {
        return DownlineMembers;
    }

    public void setDownlineMembers(Integer downlineMembers) {
        DownlineMembers = downlineMembers;
    }

    public Double getGBV() {
        return GBV;
    }

    public void setGBV(Double GBV) {
        this.GBV = GBV;
    }

    public Integer getLeg() {
        return Leg;
    }

    public void setLeg(Integer leg) {
        Leg = leg;
    }

}
