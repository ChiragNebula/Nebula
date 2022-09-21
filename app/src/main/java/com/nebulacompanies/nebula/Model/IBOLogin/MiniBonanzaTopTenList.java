package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 10-Apr-18.
 */

public class MiniBonanzaTopTenList {

    @SerializedName("Leg")
    @Expose
    private Integer Leg;

    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("IBOUser")
    @Expose
    private String IBOUser;

    @SerializedName("SaleCount")
    @Expose
    private Integer SaleCount;

    public Integer getLeg() {
        return Leg;
    }

    public void setLeg(Integer leg) {
        Leg = leg;
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

    public Integer getSaleCount() {
        return SaleCount;
    }

    public void setSaleCount(Integer saleCount) {
        SaleCount = saleCount;
    }
}
