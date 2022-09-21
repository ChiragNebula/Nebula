package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 14-03-2018.
 */

public class AchieverCustomerList {

    @SerializedName("ClearDate")
    @Expose
    private long Date;

    @SerializedName("CustomerID")
    @Expose
    private String CustomerID;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("Phone")
    @Expose
    private String Phone;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("City")
    @Expose
    private String City;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
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
