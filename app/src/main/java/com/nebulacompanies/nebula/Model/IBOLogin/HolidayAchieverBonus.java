package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 11-Apr-18.
 */

public class HolidayAchieverBonus {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private HolidayAchieverBonusList data = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HolidayAchieverBonusList getData() {
        return data;
    }

    public void setData(HolidayAchieverBonusList data) {
        this.data = data;
    }
}
