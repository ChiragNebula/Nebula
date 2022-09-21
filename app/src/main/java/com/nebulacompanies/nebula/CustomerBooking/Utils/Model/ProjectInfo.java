package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectInfo {
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("Cost")
    @Expose
    private Double cost;
    @SerializedName("PaymentPlan")
    @Expose
    private String paymentPlan;
    @SerializedName("DownPayment")
    @Expose
    private String downPayment;
    @SerializedName("DownPaymentPlan")
    @Expose
    private String downPaymentPlan;
    @SerializedName("Token")
    @Expose
    private Double token;
    @SerializedName("ReciptDownPayment")
    @Expose
    private Double reciptDownPayment;
    @SerializedName("ReciptBankLoan")
    @Expose
    private Double reciptBankLoan;
    @SerializedName("Brochure")
    @Expose
    private String brochure;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getDownPaymentPlan() {
        return downPaymentPlan;
    }

    public void setDownPaymentPlan(String downPaymentPlan) {
        this.downPaymentPlan = downPaymentPlan;
    }

    public Double getToken() {
        return token;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public Double getReciptDownPayment() {
        return reciptDownPayment;
    }

    public void setReciptDownPayment(Double reciptDownPayment) {
        this.reciptDownPayment = reciptDownPayment;
    }

    public Double getReciptBankLoan() {
        return reciptBankLoan;
    }

    public void setReciptBankLoan(Double reciptBankLoan) {
        this.reciptBankLoan = reciptBankLoan;
    }

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }
}
