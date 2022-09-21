package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 11-Apr-18.
 */

public class BonanzaDetails {

    @SerializedName("BonanzaAchievers")
    @Expose
    private List<BonanzaAchieversList> BonanzaAchievers;

    @SerializedName("BonanzaIncome")
    @Expose
    private List<BonanzaIncomeList> BonanzaIncome;

    @SerializedName("BonanzaTopTenList")
    @Expose
    private List<BonanzaTopTenList> BonanzaTopTenList;

    public List<BonanzaAchieversList> getBonanzaAchievers() {
        return BonanzaAchievers;
    }

    public void setBonanzaAchievers(List<BonanzaAchieversList> bonanzaAchievers) {
        BonanzaAchievers = bonanzaAchievers;
    }

    public List<BonanzaIncomeList> getBonanzaIncome() {
        return BonanzaIncome;
    }

    public void setBonanzaIncome(List<BonanzaIncomeList> bonanzaIncome) {
        BonanzaIncome = bonanzaIncome;
    }

    public List<com.nebulacompanies.nebula.Model.IBOLogin.BonanzaTopTenList> getBonanzaTopTenList() {
        return BonanzaTopTenList;
    }

    public void setBonanzaTopTenList(List<com.nebulacompanies.nebula.Model.IBOLogin.BonanzaTopTenList> bonanzaTopTenList) {
        BonanzaTopTenList = bonanzaTopTenList;
    }
}
