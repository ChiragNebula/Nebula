package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 24-01-2018.
 */

public class SpotIncomeList {

    @SerializedName("ClosingDate")
    @Expose
    private long Date;

    @SerializedName("Amount")
    @Expose
    private Integer Amount;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    /* @SerializedName("SpotIBOUser")
    @Expose
    private String IBOName;

    @SerializedName("AmountPaid")
    @Expose
    private double AmountPaid;

    @SerializedName("SV")
    @Expose
    private double SpotIncome;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getIBOName() {
        return IBOName;
    }

    public void setIBOName(String IBOName) {
        this.IBOName = IBOName;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        AmountPaid = amountPaid;
    }

    public double getSpotIncome() {
        return SpotIncome;
    }

    public void setSpotIncome(double spotIncome) {
        SpotIncome = spotIncome;
    }*/
}
