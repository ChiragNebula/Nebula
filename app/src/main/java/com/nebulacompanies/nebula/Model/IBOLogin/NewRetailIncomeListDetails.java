package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 12-06-2018.
 */

public class NewRetailIncomeListDetails {

    @SerializedName("Amount")
    @Expose
    private Integer Amount;

    @SerializedName("ClosingDate")
    @Expose
    private long ClosingDate;

    @SerializedName("RetailIncomeMoreInfo")
    @Expose
    private List<NewSubRetailIncomeListDetails> data = null;

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public long getClosingDate() {
        return ClosingDate;
    }

    public void setClosingDate(long closingDate) {
        ClosingDate = closingDate;
    }

    public List<NewSubRetailIncomeListDetails> getData() {
        return data;
    }

    public void setData(List<NewSubRetailIncomeListDetails> data) {
        this.data = data;
    }
}
