package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 13-07-2018.
 */

public class SponsorIncomeList {

    @SerializedName("SaleID")
    @Expose
    private Integer SaleID;

    @SerializedName("Unit")
    @Expose
    private String Unit;

    @SerializedName("PaymentFor")
    @Expose
    private String For;

    @SerializedName("SponsorIncome")
    @Expose
    private Integer SponsorIncome;

    @SerializedName("ClosingDate")
    @Expose
    private long Date;


    public Integer getSaleID() {
        return SaleID;
    }

    public void setSaleID(Integer saleID) {
        SaleID = saleID;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getFor() {
        return For;
    }

    public void setFor(String aFor) {
        For = aFor;
    }

    public Integer getSponsorIncome() {
        return SponsorIncome;
    }

    public void setSponsorIncome(Integer sponsorIncome) {
        SponsorIncome = sponsorIncome;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }
}
