package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 27-11-2017.
 */

public class StarPoolIncomeListDetails {
    @SerializedName("StarPoolIncomeId")
    @Expose
    private Integer starPoolIncomeId;

    @SerializedName("StarPoolIncomeDate")
    @Expose
    private String starPoolIncomeDate;

    @SerializedName("StarPoolIncomeRank")
    @Expose
    private String starPoolIncomeRank;


    @SerializedName("StarPoolTotalIncome")
    @Expose
    private Integer starPoolTotalIncome;

    @SerializedName("StarPoolIncome_")
    @Expose
    private Integer starPoolIncome_;

    @SerializedName("StarPoolIncomeTDS")
    @Expose
    private Integer starPoolIncomeTDS;

    @SerializedName("StarPoolIncomeNetAmount")
    @Expose
    private Integer starPoolIncomeNetAmount;

    @SerializedName("StarPoolIncomeShares")
    @Expose
    private Integer starPoolIncomeShares;

    public Integer getStarPoolIncomeId() {
        return starPoolIncomeId;
    }

    public void setStarPoolIncomeId(Integer starPoolIncomeId) {
        this.starPoolIncomeId = starPoolIncomeId;
    }

    public String getStarPoolIncomeDate() {
        return starPoolIncomeDate;
    }

    public void setStarPoolIncomeDate(String starPoolIncomeDate) {
        this.starPoolIncomeDate = starPoolIncomeDate;
    }

    public String getStarPoolIncomeRank() {
        return starPoolIncomeRank;
    }

    public void setStarPoolIncomeRank(String starPoolIncomeRank) {
        this.starPoolIncomeRank = starPoolIncomeRank;
    }

    public Integer getStarPoolTotalIncome() {
        return starPoolTotalIncome;
    }

    public void setStarPoolTotalIncome(Integer starPoolTotalIncome) {
        this.starPoolTotalIncome = starPoolTotalIncome;
    }

    public Integer getStarPoolIncome_() {
        return starPoolIncome_;
    }

    public void setStarPoolIncome_(Integer starPoolIncome_) {
        this.starPoolIncome_ = starPoolIncome_;
    }

    public Integer getStarPoolIncomeTDS() {
        return starPoolIncomeTDS;
    }

    public void setStarPoolIncomeTDS(Integer starPoolIncomeTDS) {
        this.starPoolIncomeTDS = starPoolIncomeTDS;
    }

    public Integer getStarPoolIncomeNetAmount() {
        return starPoolIncomeNetAmount;
    }

    public void setStarPoolIncomeNetAmount(Integer starPoolIncomeNetAmount) {
        this.starPoolIncomeNetAmount = starPoolIncomeNetAmount;
    }

    public Integer getStarPoolIncomeShares() {
        return starPoolIncomeShares;
    }

    public void setStarPoolIncomeShares(Integer starPoolIncomeShares) {
        this.starPoolIncomeShares = starPoolIncomeShares;
    }
}
