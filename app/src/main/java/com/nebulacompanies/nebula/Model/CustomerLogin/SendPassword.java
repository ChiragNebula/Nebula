package com.nebulacompanies.nebula.Model.CustomerLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendPassword {
    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;

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
}
