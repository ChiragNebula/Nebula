package com.nebulacompanies.nebula.Model.CustomerLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 06-Mar-18.
 */

public class CustomerDetailList {

    @SerializedName("IBOKeyID")
    @Expose
    private String IBOKeyID;
    @SerializedName("IBOID")
    @Expose
    private String IBOID;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("InvestmentAmount")
    @Expose
    private Integer InvestmentAmount;
    @SerializedName("InstallmentAmount")
    @Expose
    private Integer InstallmentAmount;
    @SerializedName("DOB")
    @Expose
    private long DOB;
    @SerializedName("Createdate")
    @Expose
    private long Createdate;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("ProductSaleID")
    @Expose
    private String ProductSaleID;
    @SerializedName("ArrivalDate")
    @Expose
    private long ArrivalDate;
    @SerializedName("PassportNo")
    @Expose
    private String PassportNo;
    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;
    @SerializedName("ClearDate")
    @Expose
    private long ClearDate;
    @SerializedName("MessageStatus")
    @Expose
    private String MessageStatus;
    @SerializedName("Gender")
    @Expose
    private String Gender;


    public String getIBOKeyID() {
        return IBOKeyID;
    }

    public void setIBOKeyID(String IBOKeyID) {
        this.IBOKeyID = IBOKeyID;
    }

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getInvestmentAmount() {
        return InvestmentAmount;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        InvestmentAmount = investmentAmount;
    }

    public Integer getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public long getDOB() {
        return DOB;
    }

    public void setDOB(long DOB) {
        this.DOB = DOB;
    }

    public long getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(long createdate) {
        Createdate = createdate;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getProductSaleID() {
        return ProductSaleID;
    }

    public void setProductSaleID(String productSaleID) {
        ProductSaleID = productSaleID;
    }

    public long getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(long arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getPassportNo() {
        return PassportNo;
    }

    public void setPassportNo(String passportNo) {
        PassportNo = passportNo;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public long getClearDate() {
        return ClearDate;
    }

    public void setClearDate(long clearDate) {
        ClearDate = clearDate;
    }

    public String getMessageStatus() {
        return MessageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        MessageStatus = messageStatus;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Integer getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(Integer passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    public Object getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(Object emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public Object getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Object emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Object getRoomSharing() {
        return roomSharing;
    }

    public void setRoomSharing(Object roomSharing) {
        this.roomSharing = roomSharing;
    }

    public Object getDoubleBed() {
        return doubleBed;
    }

    public void setDoubleBed(Object doubleBed) {
        this.doubleBed = doubleBed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @SerializedName("PassportExpiryDate")
    @Expose
    private Integer passportExpiryDate;
    @SerializedName("EmergencyContactName")
    @Expose
    private Object emergencyContactName;
    @SerializedName("EmergencyContact")
    @Expose
    private Object emergencyContact;
    @SerializedName("RoomSharing")
    @Expose
    private Object roomSharing;
    @SerializedName("DoubleBed")
    @Expose
    private Object doubleBed;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Pincode")
    @Expose
    private String pincode;
}
