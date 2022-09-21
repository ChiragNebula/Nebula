package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 12-06-2018.
 */

public class NewSubRetailIncomeListDetails {

    @SerializedName("ClearDate")
    @Expose
    private long ClearDate;

    @SerializedName("Customer")
    @Expose
    private String CustomerName;

    @SerializedName("Unit")
    @Expose
    private String Unit;

    @SerializedName("InstrumentNo")
    @Expose
    private String InstrumentNo;


    @SerializedName("Amount")
    @Expose
    private Integer Amount;

    public long getClearDate() {
        return ClearDate;
    }

    public void setClearDate(long clearDate) {
        ClearDate = clearDate;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getInstrumentNo() {
        return InstrumentNo;
    }

    public void setInstrumentNo(String instrumentNo) {
        InstrumentNo = instrumentNo;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
