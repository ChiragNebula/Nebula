package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 11-Apr-18.
 */

public class HolidayAchieverBonusList {

    @SerializedName("IBOKeyID")
    @Expose
    private String IBOKeyID;

    @SerializedName("LongAchieverDate")
    @Expose
    private long LongAchieverDate;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    public String getIBOKeyID() {
        return IBOKeyID;
    }

    public void setIBOKeyID(String IBOKeyID) {
        this.IBOKeyID = IBOKeyID;
    }

    public long getLongAchieverDate() {
        return LongAchieverDate;
    }

    public void setLongAchieverDate(long longAchieverDate) {
        LongAchieverDate = longAchieverDate;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
