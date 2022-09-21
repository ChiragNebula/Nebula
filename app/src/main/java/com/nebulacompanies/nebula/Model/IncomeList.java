package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 24-Nov-17.
 */

public class IncomeList {

    @SerializedName("StatusCode")
    @Expose
    private Integer statuscode;
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private List<IncomeListDetails> data = null;

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IncomeListDetails> getData() {
        return data;
    }

    public void setData(List<IncomeListDetails> data) {
        this.data = data;
    }
}
