package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 22-02-2018.
 */

public class IncomeSummaryDetails {

    @SerializedName("TotalIncome")
    @Expose
    private Integer TotalIncome;

    @SerializedName("RecentIncomeDate")
    @Expose
    private long RecentIncomeDate;

    @SerializedName("RecentIncome")
    @Expose
    private Integer RecentIncome;

    public Integer getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        TotalIncome = totalIncome;
    }

    public long getRecentIncomeDate() {
        return RecentIncomeDate;
    }

    public void setRecentIncomeDate(long recentIncomeDate) {
        RecentIncomeDate = recentIncomeDate;
    }

    public Integer getRecentIncome() {
        return RecentIncome;
    }

    public void setRecentIncome(Integer recentIncome) {
        RecentIncome = recentIncome;
    }
}
