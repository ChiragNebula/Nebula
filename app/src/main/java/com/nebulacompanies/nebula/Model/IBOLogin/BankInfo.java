
package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankInfo {

    @SerializedName("AccountHolderName")
    @Expose
    private String accountHolderName;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("Bank")
    @Expose
    private String bank;
    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("BranchCity")
    @Expose
    private String branchCity;
    @SerializedName("IFSCCode")
    @Expose
    private String iFSCCode;

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getIFSCCode() {
        return iFSCCode;
    }

    public void setIFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }
}
