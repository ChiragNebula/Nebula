package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 25-01-2018.
 */

public class BoosterIncomeList {

    @SerializedName("Unit")
    @Expose
    private String Unit;

    @SerializedName("CustomerUsername")
    @Expose
    private String ID;

    @SerializedName("CustomerName")
    @Expose
    private String Name;

    @SerializedName("PaymentPlan")
    @Expose
    private String PaymentPlan;

    @SerializedName("RecievedAmount")
    @Expose
    private Integer AmountRecieved;

    @SerializedName("PendingAmount")
    @Expose
    private Integer PendingAmount;

    @SerializedName("InstallmentAmount")
    @Expose
    private Integer InstallmentAmount;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("SaleType")
    @Expose
    private String SaleType;

    @SerializedName("Percent")
    @Expose
    private Integer Percent;


    /*
    @SerializedName("percent")
    @Expose
    private double Percent;*/

    public Integer getAmountRecieved() {
        return AmountRecieved;
    }

    public void setAmountRecieved(Integer amountRecieved) {
        AmountRecieved = amountRecieved;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPaymentPlan() {
        return PaymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        PaymentPlan = paymentPlan;
    }



    public Integer getPendingAmount() {
        return PendingAmount;
    }

    public void setPendingAmount(Integer pendingAmount) {
        PendingAmount = pendingAmount;
    }

    public Integer getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getSaleType() {
        return SaleType;
    }

    public void setSaleType(String saleType) {
        SaleType = saleType;
    }

    public Integer getPercent() {
        return Percent;
    }

    public void setPercent(Integer percent) {
        Percent = percent;
    }
}
