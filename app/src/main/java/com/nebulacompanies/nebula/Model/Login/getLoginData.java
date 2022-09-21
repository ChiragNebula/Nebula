package com.nebulacompanies.nebula.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getLoginData {

    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("OTp")
    @Expose
    private Integer oTp;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getOTp() {
        return oTp;
    }

    public void setOTp(Integer oTp) {
        this.oTp = oTp;
    }
}
