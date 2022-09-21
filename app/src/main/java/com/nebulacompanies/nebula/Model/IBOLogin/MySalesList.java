package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 21-02-2018.
 */

public class MySalesList {

    @SerializedName("ProductSaleId")
    @Expose
    private Integer SaleId;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("Unit")
    @Expose
    private String Unit;

    @SerializedName("BookingDate")
    @Expose
    private long BookingDate;

    @SerializedName("Sex")
    @Expose
    private String Sex;

    @SerializedName("State")
    @Expose
    private String State;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Pincode")
    @Expose
    private String Pincode;

    @SerializedName("InvestmentAmount")
    @Expose
    private Integer InvestmentAmount;

    @SerializedName("PaymentPlan")
    @Expose
    private String PaymentPlan;

    @SerializedName("PaidAmount")
    @Expose
    private Integer Receipt;

    @SerializedName("TokenAmount")
    @Expose
    private Integer TokenAmount;

    @SerializedName("DownPaymentAmount")
    @Expose
    private Integer DownPaymentAmount;

    @SerializedName("DiscountAmount")
    @Expose
    private Integer DiscountAmount;


    @SerializedName("InstallmentAmount")
    @Expose
    private Integer InstallmentAmount;

    @SerializedName("NextInstallmentDate")
    @Expose
    private long InstallmentDate;

    @SerializedName("Category")
    @Expose
    private String Category;

    @SerializedName("PackageStatus")
    @Expose
    private String PackageStatus;

    @SerializedName("PaymentSchedule")
    @Expose
    private List<SalesTelecallerList> PaymentSchedule;

    public Integer getSaleId() {
        return SaleId;
    }

    public void setSaleId(Integer saleId) {
        SaleId = saleId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public long getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(long bookingDate) {
        BookingDate = bookingDate;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public Integer getInvestmentAmount() {
        return InvestmentAmount;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        InvestmentAmount = investmentAmount;
    }

    public String getPaymentPlan() {
        return PaymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        PaymentPlan = paymentPlan;
    }

    public Integer getReceipt() {
        return Receipt;
    }

    public void setReceipt(Integer receipt) {
        Receipt = receipt;
    }

    public Integer getTokenAmount() {
        return TokenAmount;
    }

    public void setTokenAmount(Integer tokenAmount) {
        TokenAmount = tokenAmount;
    }

    public Integer getDownPaymentAmount() {
        return DownPaymentAmount;
    }

    public void setDownPaymentAmount(Integer downPaymentAmount) {
        DownPaymentAmount = downPaymentAmount;
    }

    public Integer getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        DiscountAmount = discountAmount;
    }

    public Integer getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public long getInstallmentDate() {
        return InstallmentDate;
    }

    public void setInstallmentDate(long installmentDate) {
        InstallmentDate = installmentDate;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPackageStatus() {
        return PackageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        PackageStatus = packageStatus;
    }

    public List<SalesTelecallerList> getPaymentSchedule() {
        return PaymentSchedule;
    }

    public void setPaymentSchedule(List<SalesTelecallerList> paymentSchedule) {
        PaymentSchedule = paymentSchedule;
    }
}
