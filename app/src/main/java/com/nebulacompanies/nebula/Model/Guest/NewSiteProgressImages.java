package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewSiteProgressImages {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private NewSiteProgress data = null;

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

    public NewSiteProgress getData() {
        return data;
    }

    public void setData(NewSiteProgress data) {
        this.data = data;
    }
}
