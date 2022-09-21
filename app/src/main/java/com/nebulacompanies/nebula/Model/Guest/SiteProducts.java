package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 16-Jan-18.
 */

public class SiteProducts {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<SiteProductList> data = null;

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<SiteProductList> getData() {return data;}
    public void setData(List<SiteProductList> data) {
        this.data=data;
    }

}
