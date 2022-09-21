package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 27-Nov-17.
 */

public class RetailIncomeMoreInfo {

    @SerializedName("InstallmentDate")
    @Expose
    private long date_;

    @SerializedName("ClearDate")
    @Expose
    private long cleardate_;

    @SerializedName("InstrumentNo")
    @Expose
    private String particular;

    @SerializedName("Amount")
    @Expose
    private String amount;

    @SerializedName("Status")
    @Expose
    private String status;

    public long getDate_() {
        return date_;
    }

    public void setDate_(long date_) {
        this.date_ = date_;
    }

    public long getCleardate_() {
        return cleardate_;
    }

    public void setCleardate_(long cleardate_) {
        this.cleardate_ = cleardate_;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
