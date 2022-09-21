package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyApplicantInfo {

    @SerializedName("profileUrl")
    @Expose
    private String profileUrl;
    @SerializedName("ChildrenCount")
    @Expose
    private String childrenCount;
    @SerializedName("CommunicationAddress")
    @Expose
    private String communicationAddress;
    @SerializedName("CommunicationCity")
    @Expose
    private String communicationCity;
    @SerializedName("CommunicationCountry")
    @Expose
    private String communicationCountry;
    @SerializedName("CommunicationState")
    @Expose
    private String communicationState;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("DateOfBirth")
    @Expose
    private long dateOfBirth;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("Fax")
    @Expose
    private String fax;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("MaritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("OfficeContactNo")
    @Expose
    private String officeContactNo;
    @SerializedName("Profession")
    @Expose
    private String profession;
    @SerializedName("RelationType")
    @Expose
    private String relationType;
    @SerializedName("RelativeName")
    @Expose
    private String relativeName;
    @SerializedName("ResidentialAddress")
    @Expose
    private String residentialAddress;
    @SerializedName("ResidentialCity")
    @Expose
    private String residentialCity;
    @SerializedName("ResidentialCountry")
    @Expose
    private String residentialCountry;
    @SerializedName("ResidentialState")
    @Expose
    private String residentialState;
    @SerializedName("ResidentialStatus")
    @Expose
    private String residentialStatus;
    @SerializedName("TelephoneResidential")
    @Expose
    private String telephoneResidential;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PrefrenceApp")
    @Expose
    private PrefrenceApp prefrenceApp;

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(String childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getCommunicationAddress() {
        return communicationAddress + ", " + communicationCity + ", " + communicationState + ", " + communicationCountry;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getCommunicationCity() {
        return communicationCity;
    }

    public void setCommunicationCity(String communicationCity) {
        this.communicationCity = communicationCity;
    }

    public String getCommunicationCountry() {
        return communicationCountry;
    }

    public void setCommunicationCountry(String communicationCountry) {
        this.communicationCountry = communicationCountry;
    }

    public String getCommunicationState() {
        return communicationState;
    }

    public void setCommunicationState(String communicationState) {
        this.communicationState = communicationState;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName + " ";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName + " ";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMiddleName() {
        return middleName + " ";
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOfficeContactNo() {
        return officeContactNo;
    }

    public void setOfficeContactNo(String officeContactNo) {
        this.officeContactNo = officeContactNo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getResidentialAddress() {
        return residentialAddress + ", " + residentialCity + ", " + residentialState + ", " + residentialCountry;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getResidentialCity() {
        return residentialCity;
    }

    public void setResidentialCity(String residentialCity) {
        this.residentialCity = residentialCity;
    }

    public String getResidentialCountry() {
        return residentialCountry;
    }

    public void setResidentialCountry(String residentialCountry) {
        this.residentialCountry = residentialCountry;
    }

    public String getResidentialState() {
        return residentialState;
    }

    public void setResidentialState(String residentialState) {
        this.residentialState = residentialState;
    }

    public String getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(String residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public String getTelephoneResidential() {
        return telephoneResidential;
    }

    public void setTelephoneResidential(String telephoneResidential) {
        this.telephoneResidential = telephoneResidential;
    }

    public String getTitle() {
        return title + " ";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PrefrenceApp getPrefrenceApp() {
        return prefrenceApp;
    }

    public void setPrefrenceApp(PrefrenceApp prefrenceApp) {
        this.prefrenceApp = prefrenceApp;
    }

}
