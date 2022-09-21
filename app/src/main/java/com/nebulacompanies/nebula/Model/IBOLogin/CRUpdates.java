package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 16-Apr-18.
 */

public class CRUpdates {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private List<CRUpdatesList> data;

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

    public List<CRUpdatesList> getData() {
        return data;
    }

    public void setData(List<CRUpdatesList> data) {
        this.data = data;
    }
}
