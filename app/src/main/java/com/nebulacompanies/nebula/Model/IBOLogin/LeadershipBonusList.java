package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 23-01-2018.
 */

public class LeadershipBonusList {

    @SerializedName("IncomeDate")
    @Expose
    private long Date;

    @SerializedName("Income")
    @Expose
    private Integer BonusPayment;

    @SerializedName("TDS")
    @Expose
    private Integer TDS;

    @SerializedName("NetAmount")
    @Expose
    private Integer PayoutAmount;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public Integer getBonusPayment() {
        return BonusPayment;
    }

    public void setBonusPayment(Integer bonusPayment) {
        BonusPayment = bonusPayment;
    }

    public Integer getTDS() {
        return TDS;
    }

    public void setTDS(Integer TDS) {
        this.TDS = TDS;
    }

    public Integer getPayoutAmount() {
        return PayoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        PayoutAmount = payoutAmount;
    }
}
