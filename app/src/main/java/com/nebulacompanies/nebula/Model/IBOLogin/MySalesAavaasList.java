package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 19-01-2018.
 */

public class MySalesAavaasList {

    @SerializedName("Sale_ID")
    @Expose
    private Integer SaleID;

    @SerializedName("Subcategory")
    @Expose
    private String Category;

    @SerializedName("HydderabadProduct")
    @Expose
    private String ProductName;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("Apartment")
    @Expose
    private String Apartment;

    @SerializedName("Purchase_Date")
    @Expose
    private String PurchaseDate;

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

    @SerializedName("Investment")
    @Expose
    private Integer Investment;

    @SerializedName("Payment_Options")
    @Expose
    private String PaymentOptions;


    @SerializedName("Receipt")
    @Expose
    private Integer Receipt;

    @SerializedName("Token_Amount")
    @Expose
    private Integer TokenAmount;

    @SerializedName("C20__Amount")
    @Expose
    private Integer C20Amount;

    @SerializedName("Discount")
    @Expose
    private Integer Discount;

    @SerializedName("Installment")
    @Expose
    private Integer Installment;

    @SerializedName("Installment_Date")
    @Expose
    private String InstallmentDate;

    public Integer getSaleID() {
        return SaleID;
    }

    public void setSaleID(Integer saleID) {
        SaleID = saleID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public String getApartment() {
        return Apartment;
    }

    public void setApartment(String apartment) {
        Apartment = apartment;
    }

    public String getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        PurchaseDate = purchaseDate;
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

    public Integer getInvestment() {
        return Investment;
    }

    public void setInvestment(Integer investment) {
        Investment = investment;
    }

    public String getPaymentOptions() {
        return PaymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        PaymentOptions = paymentOptions;
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

    public Integer getC20Amount() {
        return C20Amount;
    }

    public void setC20Amount(Integer c20Amount) {
        C20Amount = c20Amount;
    }

    public Integer getDiscount() {
        return Discount;
    }

    public void setDiscount(Integer discount) {
        Discount = discount;
    }

    public Integer getInstallment() {
        return Installment;
    }

    public void setInstallment(Integer installment) {
        Installment = installment;
    }

    public String getInstallmentDate() {
        return InstallmentDate;
    }

    public void setInstallmentDate(String installmentDate) {
        InstallmentDate = installmentDate;
    }
}
