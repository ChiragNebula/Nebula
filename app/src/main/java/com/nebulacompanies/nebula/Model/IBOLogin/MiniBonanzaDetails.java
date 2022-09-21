package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 22-Mar-18.
 */

public class MiniBonanzaDetails {

    /*@SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("Node")
    @Expose
    private List<MiniBonanzaAchieverList> Node;

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public List<MiniBonanzaAchieverList> getNode() {
        return Node;
    }

    public void setNode(List<MiniBonanzaAchieverList> node) {
        Node = node;
    }*/

    @SerializedName("MiniBonanzaAchievers")
    @Expose
    private List<MiniBonanzaAchieverList> MiniBonanzaAchievers;

    @SerializedName("MiniBonanzaIncome")
    @Expose
    private List<MiniBonanzaIncomeList> MiniBonanzaIncome;

    @SerializedName("MiniBonanzaTopTenList")
    @Expose
    private List<MiniBonanzaTopTenList> MiniBonanzaTopTenList;


    public List<MiniBonanzaAchieverList> getMiniBonanzaAchievers() {
        return MiniBonanzaAchievers;
    }

    public void setMiniBonanzaAchievers(List<MiniBonanzaAchieverList> miniBonanzaAchievers) {
        MiniBonanzaAchievers = miniBonanzaAchievers;
    }

    public List<MiniBonanzaIncomeList> getMiniBonanzaIncome() {
        return MiniBonanzaIncome;
    }

    public void setMiniBonanzaIncome(List<MiniBonanzaIncomeList> miniBonanzaIncome) {
        MiniBonanzaIncome = miniBonanzaIncome;
    }

    public List<com.nebulacompanies.nebula.Model.IBOLogin.MiniBonanzaTopTenList> getMiniBonanzaTopTenList() {
        return MiniBonanzaTopTenList;
    }

    public void setMiniBonanzaTopTenList(List<com.nebulacompanies.nebula.Model.IBOLogin.MiniBonanzaTopTenList> miniBonanzaTopTenList) {
        MiniBonanzaTopTenList = miniBonanzaTopTenList;
    }

}
