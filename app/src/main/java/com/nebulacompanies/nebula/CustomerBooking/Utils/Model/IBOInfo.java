package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IBOInfo {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("City")
    @Expose
    private String city;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
