package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import static com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils.IndianCurrencyFormat;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Utils.AppUtils.DisplayDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Nebula_18 on 11/22/2017.
 */

public class InstallmentInfo {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Installment")
    @Expose
    private String installment;
    @SerializedName("EMIDate")
    @Expose
    private long eMIDate;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("PaymentType")
    @Expose
    private String paymentType;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ClearDate")
    @Expose
    private long clearDate;

    public String getTXNNo() {
        return TXNNo;
    }

    public void setTXNNo(String TXNNo) {
        this.TXNNo = TXNNo;
    }

    @SerializedName("TXNNo")
    @Expose
    private String TXNNo;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getEMIDate() {
        return DisplayDate(eMIDate);
    }

    public void setEMIDate(long eMIDate) {
        this.eMIDate = eMIDate;
    }

    public String getAmount() {
        return "₹ " + IndianCurrencyFormat.format(amount);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClearDate() {
        return DisplayDate(clearDate);
    }

    public void setClearDate(long clearDate) {
        this.clearDate = clearDate;
    }
}
