package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 22-Feb-18.
 */

public class NewJoineeList {

    @SerializedName("IBOID")
    @Expose
    private String IBOID;
    @SerializedName("IBOName")
    @Expose
    private String IBOName;
    @SerializedName("DOJ")
    @Expose
    private long DOJ;
    @SerializedName("Sex")
    @Expose
    private String Sex;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("State")
    @Expose
    private String State;

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

    public long getDOJ() {
        return DOJ;
    }

    public void setDOJ(long DOJ) {
        this.DOJ = DOJ;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
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
}
