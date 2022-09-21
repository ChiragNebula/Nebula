package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 27-Feb-18.
 */

public class IBODetailList {

    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("Gender")
    @Expose
    private String Gender;
    @SerializedName("MartialStatusIsMarriage")
    @Expose
    private boolean MartialStatusIsMarriage;
    @SerializedName("DOB")
    @Expose
    private long DOB;
    @SerializedName("AddressLine1")
    @Expose
    private String AddressLine1;
    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;
    @SerializedName("AlternateMobile")
    @Expose
    private String AlternateMobile;
    @SerializedName("State")
    @Expose
    private String State;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("PinCode")
    @Expose
    private String PinCode;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("PassportNo")
    @Expose
    private String PassportNo;
    @SerializedName("PassportExpiryDate")
    @Expose
    private long PassportExpiryDate;
    @SerializedName("Id")
    @Expose
    private String Id;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public boolean isMartialStatusIsMarriage() {
        return MartialStatusIsMarriage;
    }

    public void setMartialStatusIsMarriage(boolean martialStatusIsMarriage) {
        MartialStatusIsMarriage = martialStatusIsMarriage;
    }

    public long getDOB() {
        return DOB;
    }

    public void setDOB(long DOB) {
        this.DOB = DOB;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAlternateMobile() {
        return AlternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        AlternateMobile = alternateMobile;
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

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassportNo() {
        return PassportNo;
    }

    public void setPassportNo(String passportNo) {
        PassportNo = passportNo;
    }

    public long getPassportExpiryDate() {
        return PassportExpiryDate;
    }

    public void setPassportExpiryDate(long passportExpiryDate) {
        PassportExpiryDate = passportExpiryDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
