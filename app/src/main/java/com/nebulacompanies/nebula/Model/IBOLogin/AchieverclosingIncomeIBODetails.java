package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 12-Apr-18.
 */

public class AchieverclosingIncomeIBODetails {

    @SerializedName("IBOkeyID")
    @Expose
    private String IBOkeyID;

    @SerializedName("IBOID")
    @Expose
    private String IBOID;

    @SerializedName("CustomerType")
    @Expose
    private String CustomerType;

    @SerializedName("TotalAmount")
    @Expose
    private Integer TotalAmount;

    @SerializedName("HolidayAchieveIBOMasterID")
    @Expose
    private Integer HolidayAchieveIBOMasterID;

    @SerializedName("LongClearDate")
    @Expose
    private long LongClearDate;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Phone")
    @Expose
    private String Phone;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("City")
    @Expose
    private String City;

    public String getIBOkeyID() {
        return IBOkeyID;
    }

    public void setIBOkeyID(String IBOkeyID) {
        this.IBOkeyID = IBOkeyID;
    }

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public Integer getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        TotalAmount = totalAmount;
    }

    public Integer getHolidayAchieveIBOMasterID() {
        return HolidayAchieveIBOMasterID;
    }

    public void setHolidayAchieveIBOMasterID(Integer holidayAchieveIBOMasterID) {
        HolidayAchieveIBOMasterID = holidayAchieveIBOMasterID;
    }

    public long getLongClearDate() {
        return LongClearDate;
    }

    public void setLongClearDate(long longClearDate) {
        LongClearDate = longClearDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

}
