package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 24-Nov-17.
 */

public class IncomeListDetails {
    @SerializedName("IncomeId")
    @Expose
    private Integer incomeId;
    @SerializedName("IncomeIcon")
    @Expose
    private String incomeUrl;

    @SerializedName("IncomeName")
    @Expose
    private String incomeTitle;

    @SerializedName("Income")
    @Expose
    private int incomeAmount;

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeUrl() {
        return incomeUrl;
    }

    public void setIncomeUrl(String incomeUrl) {
        this.incomeUrl = incomeUrl;
    }

    public String getIncomeTitle() {
        return incomeTitle;
    }

    public void setIncomeTitle(String incomeTitle) {
        this.incomeTitle = incomeTitle;
    }

    public int getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(int incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

}
