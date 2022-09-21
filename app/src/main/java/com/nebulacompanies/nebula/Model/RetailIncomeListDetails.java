package com.nebulacompanies.nebula.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 27-Nov-17.
 */

public class RetailIncomeListDetails {

    @SerializedName("RetailIncomeId")
    @Expose
    private Integer retailIncomeProjectId;

    @SerializedName("Project")
    @Expose
    private String retailIncomeProject;

    @SerializedName("CustomerName")
    @Expose
    private String retailIncomeCustomerName;

    @SerializedName("Unit")
    @Expose
    private String retailIncomeUnitNumber;

    @SerializedName("RetailProfit")
    @Expose
    private Integer retailIncomeAmount;

    public Integer getRetailIncomeProjectId() {
        return retailIncomeProjectId;
    }

    public void setRetailIncomeProjectId(Integer retailIncomeProjectId) {
        this.retailIncomeProjectId = retailIncomeProjectId;
    }

    public String getRetailIncomeProject() {
        return retailIncomeProject;
    }

    public void setRetailIncomeProject(String retailIncomeProject) {
        this.retailIncomeProject = retailIncomeProject;
    }

    public String getRetailIncomeCustomerName() {
        return retailIncomeCustomerName;
    }

    public void setRetailIncomeCustomerName(String retailIncomeCustomerName) {
        this.retailIncomeCustomerName = retailIncomeCustomerName;
    }

    public String getRetailIncomeUnitNumber() {
        return retailIncomeUnitNumber;
    }

    public void setRetailIncomeUnitNumber(String retailIncomeUnitNumber) {
        this.retailIncomeUnitNumber = retailIncomeUnitNumber;
    }

    public Integer getRetailIncomeAmount() {
        return retailIncomeAmount;
    }

    public void setRetailIncomeAmount(Integer retailIncomeAmount) {
        this.retailIncomeAmount = retailIncomeAmount;
    }

    public List<RetailIncomeMoreInfo> getRetailIncomeMoreInfo() {
        return retailIncomeMoreInfo;
    }

    public void setRetailIncomeMoreInfo(List<RetailIncomeMoreInfo> retailIncomeMoreInfo) {
        this.retailIncomeMoreInfo = retailIncomeMoreInfo;
    }

    @SerializedName("RetailIncomeMoreInfo")
    @Expose
    private List<RetailIncomeMoreInfo> retailIncomeMoreInfo = null;



}

