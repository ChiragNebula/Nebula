package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 29-01-2018.
 */

public class HolidayDownlineDetails {

    @SerializedName("Statuscode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    HolidayDownlineList data = null;

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

    public HolidayDownlineList getData() {
        return data;
    }

    public void setData(HolidayDownlineList data) {
        this.data = data;
    }
}
