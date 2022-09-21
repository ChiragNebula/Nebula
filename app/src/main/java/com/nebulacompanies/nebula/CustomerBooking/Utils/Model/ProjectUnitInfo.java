
package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectUnitInfo implements ChipInterface {

    private Uri avatarUri;

    @Override
    public Object getId() {
        return iD;
    }

    @Override
    public Uri getAvatarUri() {
        return avatarUri;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }

    @Override
    public String getLabel() {
        return unit + ", " + project;
    }

    @Override
    public String getInfo() {
        return unitType + " " + floor + " Unit Budget : " + unitBudget + " Payment Plan : " + paymentPlan;
    }

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("UnitID")
    @Expose
    private Integer unitID;
    @SerializedName("UnitType")
    @Expose
    private String unitType;
    @SerializedName("Floor")
    @Expose
    private String floor;
    @SerializedName("Project")
    @Expose
    private String project;
    @SerializedName("IBOID")
    @Expose
    private Integer iBOID;
    @SerializedName("IBOName")
    @Expose
    private String iBOName;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("PendingEMICount")
    @Expose
    private Integer pendingEMICount;
    @SerializedName("PaymentPlan")
    @Expose
    private String paymentPlan;
    @SerializedName("UnitBudget")
    @Expose
    private Double unitBudget;
    @SerializedName("PLC")
    @Expose
    private Integer pLC;
    @SerializedName("ConstArea")
    @Expose
    private String constArea;
    @SerializedName("FirstAppName")
    @Expose
    private String firstAppName;

    public Integer getID() {
        return iD;
    }

    @SerializedName("IBOInfo")
    @Expose
    private IBOInfo iBOInfo;

    @SerializedName("AFSStatus")
    @Expose
    private Boolean aFSStatus;

    @SerializedName("ProjectInfo")
    @Expose
    private ProjectInfo projectInfo;

    public void setID(Integer iD) {
        this.iD = iD;
        this.avatarUri = null;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getIBOID() {
        return iBOID;
    }

    public void setIBOID(Integer iBOID) {
        this.iBOID = iBOID;
    }

    public String getIBOName() {
        return iBOName;
    }

    public void setIBOName(String iBOName) {
        this.iBOName = iBOName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPendingEMICount() {
        return pendingEMICount;
    }

    public void setPendingEMICount(Integer pendingEMICount) {
        this.pendingEMICount = pendingEMICount;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public Double getUnitBudget() {
        return unitBudget;
    }

    public void setUnitBudget(Double unitBudget) {
        this.unitBudget = unitBudget;
    }

    public Integer getPLC() {
        return pLC;
    }

    public void setPLC(Integer pLC) {
        this.pLC = pLC;
    }

    public String getConstArea() {
        return constArea;
    }

    public void setConstArea(String constArea) {
        this.constArea = constArea;
    }

    public String getFirstAppName() {
        return firstAppName;
    }

    public void setFirstAppName(String firstAppName) {
        this.firstAppName = firstAppName;
    }

    public IBOInfo getIBOInfo() {
        return iBOInfo;
    }

    public void setIBOInfo(IBOInfo iBOInfo) {
        this.iBOInfo = iBOInfo;
    }

    public Boolean getAFSStatus() {
        return aFSStatus;
    }

    public void setAFSStatus(Boolean aFSStatus) {
        this.aFSStatus = aFSStatus;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

   /* @SerializedName("LoanDocumentsStatus")
    @Expose
    private List<LoanDocumentsStatus> loanDocumentsStatus = null;

    public List<LoanDocumentsStatus> getLoanDocumentsStatus() {
        return loanDocumentsStatus;
    }

    public void setLoanDocumentsStatus(List<LoanDocumentsStatus> loanDocumentsStatus) {
        this.loanDocumentsStatus = loanDocumentsStatus;
    }*/
}
