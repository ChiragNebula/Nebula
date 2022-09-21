package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nebulacompanies.nebula.Model.IBOLogin.NewRetailIncomeListDetails;

import java.util.List;

/**
 * Created by Palak Mehta on 27-Nov-17.
 */

public class RetailIncomeList {
    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("Message")
    @Expose
    private String message;

    /*@SerializedName("Data")
    @Expose
    private List<RetailIncomeListDetails> data = null;*/

    @SerializedName("Data")
    @Expose
    private List<NewRetailIncomeListDetails> data = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statuscode) {
        this.statusCode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NewRetailIncomeListDetails> getData() {
        return data;
    }

    public void setData(List<NewRetailIncomeListDetails> data) {
        this.data = data;
    }
}
