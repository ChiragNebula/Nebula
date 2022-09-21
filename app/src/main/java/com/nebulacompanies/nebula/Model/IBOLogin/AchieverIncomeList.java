package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 16-03-2018.
 */

public class AchieverIncomeList {

    @SerializedName("ClosingDate")
    @Expose
    private long ClosingDate;

    @SerializedName("Amount")
    @Expose
    private Integer Amount;

    @SerializedName("HolidayAchieveIBOMasterID")
    @Expose
    private Integer HolidayAchieveIBOMasterID;

    public long getClosingDate() {
        return ClosingDate;
    }

    public void setClosingDate(long closingDate) {
        ClosingDate = closingDate;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public Integer getHolidayAchieveIBOMasterID() {
        return HolidayAchieveIBOMasterID;
    }

    public void setHolidayAchieveIBOMasterID(Integer holidayAchieveIBOMasterID) {
        HolidayAchieveIBOMasterID = holidayAchieveIBOMasterID;
    }
}
