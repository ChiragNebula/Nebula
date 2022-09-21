package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 24-01-2018.
 */

public class StarClubIncomeList {

    @SerializedName("IncomeDate")
    @Expose
    private long Date;

    @SerializedName("Rank")
    @Expose
    private String Rank;

    @SerializedName("TotalIncome")
    @Expose
    private Integer TotalIncome;

    @SerializedName("IncomeShares")
    @Expose
    private Integer ShareAmount;

    @SerializedName("IncomeTDS")
    @Expose
    private Integer TDS;

    @SerializedName("IncomeNetAmount")
    @Expose
    private Integer NetAmount;

    @SerializedName("Share")
    @Expose
    private Integer Shares;

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

    public Integer getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        TotalIncome = totalIncome;
    }

    public Integer getShareAmount() {
        return ShareAmount;
    }

    public void setShareAmount(Integer shareAmount) {
        ShareAmount = shareAmount;
    }

    public Integer getTDS() {
        return TDS;
    }

    public void setTDS(Integer TDS) {
        this.TDS = TDS;
    }

    public Integer getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(Integer netAmount) {
        NetAmount = netAmount;
    }

    public Integer getShares() {
        return Shares;
    }

    public void setShares(Integer shares) {
        Shares = shares;
    }
}
