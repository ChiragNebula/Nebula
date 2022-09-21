package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 19-01-2018.
 */

public class LastTenJoinList {

    @SerializedName("MemberID")
    @Expose
    private String IBOID;

    @SerializedName("MemberName")
    @Expose
    private String IBOName;

    @SerializedName("EntryDate")
    @Expose
    private String Date;

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
