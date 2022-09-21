package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 14-03-2018.
 */

public class HolidayAchieverList {

    @SerializedName("AchieverIBODetails")
    @Expose
    private List<AchieverIBOList> AchieverIBOList = null;

    @SerializedName("AchieverCustomerDetails")
    @Expose
    private List<AchieverCustomerList> AchieverCustomerList = null;

    @SerializedName("AchieverIncomeDetails")
    @Expose
    private List<AchieverIncomeList> AchieverIncomeDetails = null;

    @SerializedName("AchieverclosingIncomeIBODetails")
    @Expose
    private List<AchieverclosingIncomeIBODetails> AchieverclosingIncomeIBODetails = null;

   /* @SerializedName("Data")
    @Expose
    private List<AchieverIncomeDetails> data = null;*/

    public List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverIBOList> getAchieverIBOList() {
        return AchieverIBOList;
    }

    public void setAchieverIBOList(List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverIBOList> achieverIBOList) {
        AchieverIBOList = achieverIBOList;
    }

    public List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverCustomerList> getAchieverCustomerList() {
        return AchieverCustomerList;
    }

    public void setAchieverCustomerList(List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverCustomerList> achieverCustomerList) {
        AchieverCustomerList = achieverCustomerList;
    }

    public List<AchieverIncomeList> getAchieverIncomeDetails() {
        return AchieverIncomeDetails;
    }

    public void setAchieverIncomeDetails(List<AchieverIncomeList> achieverIncomeDetails) {
        AchieverIncomeDetails = achieverIncomeDetails;
    }

    public List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverclosingIncomeIBODetails> getAchieverclosingIncomeIBODetails() {
        return AchieverclosingIncomeIBODetails;
    }

    public void setAchieverclosingIncomeIBODetails(List<com.nebulacompanies.nebula.Model.IBOLogin.AchieverclosingIncomeIBODetails> achieverclosingIncomeIBODetails) {
        AchieverclosingIncomeIBODetails = achieverclosingIncomeIBODetails;
    }
}
