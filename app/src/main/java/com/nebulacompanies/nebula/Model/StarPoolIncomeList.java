package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 27-11-2017.
 */

public class StarPoolIncomeList {
    @SerializedName("Statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<StarPoolIncomeListDetails> data  = null;

    public int getStatuscode() {
        return statuscode;
    }
    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<StarPoolIncomeListDetails> getData() {return data;}
    public void setData(List<StarPoolIncomeListDetails> data) {this.data=data;}
}
