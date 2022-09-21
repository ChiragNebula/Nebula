package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 14-03-2018.
 */

public class AchieverIBOList{

    @SerializedName("ClearDate")
    @Expose
    private long Date;

    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("IBOName")
    @Expose
    private String IBOName;

    @SerializedName("Phone")
    @Expose
    private String Phone;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("City")
    @Expose
    private String City;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
