package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 28-Apr-18.
 */

public class SalesTelecallerList {

    @SerializedName("InstallmentNo")
    @Expose
    private Integer InstallmentNo;

    @SerializedName("InstallmentDate")
    @Expose
    private long InstallmentDate;

    @SerializedName("PaymentFor")
    @Expose
    private String PaymentFor;

    @SerializedName("InstallmentAmount")
    @Expose
    private Integer InstallmentAmount;

    @SerializedName("InstallmentStatus")
    @Expose
    private String InstallmentStatus;

    @SerializedName("BV")
    @Expose
    private Integer BV;

    @SerializedName("NV")
    @Expose
    private Integer NV;

    @SerializedName("PackageStatus")
    @Expose
    private String PackageStatus;

    public Integer getInstallmentNo() {
        return InstallmentNo;
    }

    public void setInstallmentNo(Integer installmentNo) {
        InstallmentNo = installmentNo;
    }

    public long getInstallmentDate() {
        return InstallmentDate;
    }

    public void setInstallmentDate(long installmentDate) {
        InstallmentDate = installmentDate;
    }

    public String getPaymentFor() {
        return PaymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        PaymentFor = paymentFor;
    }

    public Integer getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public String getInstallmentStatus() {
        return InstallmentStatus;
    }

    public void setInstallmentStatus(String installmentStatus) {
        InstallmentStatus = installmentStatus;
    }

    public Integer getBV() {
        return BV;
    }

    public void setBV(Integer BV) {
        this.BV = BV;
    }

    public Integer getNV() {
        return NV;
    }

    public void setNV(Integer NV) {
        this.NV = NV;
    }

    public String getPackageStatus() {
        return PackageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        PackageStatus = packageStatus;
    }
}
