package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 10-Apr-18.
 */

public class MiniBonanzaIncomeList {

    @SerializedName("LongToDate")
    @Expose
    private long LongToDate;

    @SerializedName("TotalAmount")
    @Expose
    private Integer TotalAmount;

    public long getLongToDate() {
        return LongToDate;
    }

    public void setLongToDate(long longToDate) {
        LongToDate = longToDate;
    }

    public Integer getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        TotalAmount = totalAmount;
    }
}
