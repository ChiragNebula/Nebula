package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 24-01-2018.
 */

public class GoldIncomeList {

    @SerializedName("ClosingDate")
    @Expose
    private long Date;

    @SerializedName("Rank")
    @Expose
    private String Rank;

    @SerializedName("GoldIncome")
    @Expose
    private Integer Income;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public Integer getIncome() {
        return Income;
    }

    public void setIncome(Integer income) {
        Income = income;
    }
}
