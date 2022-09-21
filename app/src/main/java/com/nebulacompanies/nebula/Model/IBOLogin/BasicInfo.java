
package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfo {

    @SerializedName("IBOKeyID")
    @Expose
    private String iBOKeyID;
    @SerializedName("IBOID")
    @Expose
    private String iBOID;
    @SerializedName("IBOUser")
    @Expose
    private String IBOUser;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("DOJ")
    @Expose
    private Integer dOJ;
    @SerializedName("DOJActual")
    @Expose
    private String dOJActual;
    @SerializedName("Sex")
    @Expose
    private String sex;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Rank")
    @Expose
    private String rank;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;
    @SerializedName("ProfilePicFileName")
    @Expose
    private String profilePicFileName;

    public String getIBOKeyID() {
        return iBOKeyID;
    }

    public void setIBOKeyID(String iBOKeyID) {
        this.iBOKeyID = iBOKeyID;
    }

    public String getIBOID() {
        return iBOID;
    }

    public void setIBOID(String iBOID) {
        this.iBOID = iBOID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDOJ() {
        return dOJ;
    }

    public void setDOJ(Integer dOJ) {
        this.dOJ = dOJ;
    }

    public String getDOJActual() {
        return dOJActual;
    }

    public void setDOJActual(String dOJActual) {
        this.dOJActual = dOJActual;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicFileName() {
        return profilePicFileName;
    }

    public void setProfilePicFileName(String profilePicFileName) {
        this.profilePicFileName = profilePicFileName;
    }

    public String getIBOUser() {
        return IBOUser;
    }

    public void setIBOUser(String IBOUser) {
        this.IBOUser = IBOUser;
    }
}
