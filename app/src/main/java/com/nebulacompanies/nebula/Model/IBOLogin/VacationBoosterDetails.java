package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 25-01-2018.
 */

public class VacationBoosterDetails {

    @SerializedName("Statuscode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private List<VacationBoosterList> data = null;

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

    public List<VacationBoosterList> getData() {
        return data;
    }

    public void setData(List<VacationBoosterList> data) {
        this.data = data;
    }
}
