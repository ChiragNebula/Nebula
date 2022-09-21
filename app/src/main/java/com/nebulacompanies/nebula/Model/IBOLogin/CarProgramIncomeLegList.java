package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 23-01-2018.
 */

public class CarProgramIncomeLegList {

    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("CustomerUser")
    @Expose
    private String IBOName;

    @SerializedName("Percent")
    @Expose
    private Integer Percentage;

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

    public Integer getPercentage() {
        return Percentage;
    }

    public void setPercentage(Integer percentage) {
        Percentage = percentage;
    }
}
