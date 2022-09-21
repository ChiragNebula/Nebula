package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 19-Feb-18.
 */

public class IncomeHistoryList {

    @SerializedName("IncomeDate")
    @Expose
    private long IncomeDate;
    @SerializedName("TotalAmount")
    @Expose
    private Integer TotalAmount;

    public long getIncomeDate() {
        return IncomeDate;
    }

    public void setIncomeDate(long incomeDate) {
        IncomeDate = incomeDate;
    }

    public Integer getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        TotalAmount = totalAmount;
    }
}
