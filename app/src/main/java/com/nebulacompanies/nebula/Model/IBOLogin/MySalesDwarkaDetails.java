package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 22-01-2018.
 */

public class MySalesDwarkaDetails {
    @SerializedName("Statuscode")
    @Expose
    private Integer statusCode;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private List<MySalesAavaasList> data = null;

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

    public List<MySalesAavaasList> getData() {
        return data;
    }

    public void setData(List<MySalesAavaasList> data) {
        this.data = data;
    }
}