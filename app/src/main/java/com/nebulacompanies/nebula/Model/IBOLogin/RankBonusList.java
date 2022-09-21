package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 12-07-2018.
 */

public class RankBonusList {

    @SerializedName("SaleID")
    @Expose
    private Integer message;

    @SerializedName("Unit")
    @Expose
    private String Unit;

    @SerializedName("PaymentFor")
    @Expose
    private String PaymentFor;

    @SerializedName("PlatinumRankBonus")
    @Expose
    private Integer PlatinumRankBonus;

    @SerializedName("TotalRankBonus")
    @Expose
    private Integer TotalRankBonus;

    @SerializedName("StarPlatinumRankBonus")
    @Expose
    private Integer StarPlatinumRankBonus;

    @SerializedName("ClosingDate")
    @Expose
    private long ClosingDate;

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getPaymentFor() {
        return PaymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        PaymentFor = paymentFor;
    }

    public Integer getPlatinumRankBonus() {
        return PlatinumRankBonus;
    }

    public void setPlatinumRankBonus(Integer platinumRankBonus) {
        PlatinumRankBonus = platinumRankBonus;
    }

    public Integer getTotalRankBonus() {
        return TotalRankBonus;
    }

    public void setTotalRankBonus(Integer totalRankBonus) {
        TotalRankBonus = totalRankBonus;
    }

    public Integer getStarPlatinumRankBonus() {
        return StarPlatinumRankBonus;
    }

    public void setStarPlatinumRankBonus(Integer starPlatinumRankBonus) {
        StarPlatinumRankBonus = starPlatinumRankBonus;
    }

    public long getClosingDate() {
        return ClosingDate;
    }

    public void setClosingDate(long closingDate) {
        ClosingDate = closingDate;
    }
}
