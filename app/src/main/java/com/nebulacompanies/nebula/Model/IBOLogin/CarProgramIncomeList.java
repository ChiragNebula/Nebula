package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar Virvani on 22-01-2018.
 */

public class CarProgramIncomeList {

    @SerializedName("MainLegAverage")
    @Expose
    private double MainLegAverage;

    @SerializedName("MainLegCount")
    @Expose
    private Integer MainLegCount;

    @SerializedName("OtherLegAverage")
    @Expose
    private double OtherLegAverage;

    @SerializedName("OtherLegCount")
    @Expose
    private Integer OtherLegCount;

    @SerializedName("EligibleMessage")
    @Expose
    private String eligibleMessage;

    @SerializedName("MainLeg")
    @Expose
    private List<CarProgramIncomeLegList> MainLeg;

    @SerializedName("OtherLeg")
    @Expose
    private List<CarProgramIncomeLegList> OtherLeg;

    public List<CarProgramIncomeLegList> getMainLeg() {
        return MainLeg;
    }

    public void setMainLeg(List<CarProgramIncomeLegList> mainLeg) {
        MainLeg = mainLeg;
    }

    public List<CarProgramIncomeLegList> getOtherLeg() {
        return OtherLeg;
    }

    public void setOtherLeg(List<CarProgramIncomeLegList> otherLeg) {
        OtherLeg = otherLeg;
    }

    public double getMainLegAverage() {
        return MainLegAverage;
    }

    public void setMainLegAverage(double mainLegAverage) {
        MainLegAverage = mainLegAverage;
    }

    public Integer getMainLegCount() {
        return MainLegCount;
    }

    public void setMainLegCount(Integer mainLegCount) {
        MainLegCount = mainLegCount;
    }

    public double getOtherLegAverage() {
        return OtherLegAverage;
    }

    public void setOtherLegAverage(double otherLegAverage) {
        OtherLegAverage = otherLegAverage;
    }

    public Integer getOtherLegCount() {
        return OtherLegCount;
    }

    public void setOtherLegCount(Integer otherLegCount) {
        OtherLegCount = otherLegCount;
    }

    public String getEligibleMessage() {
        return eligibleMessage;
    }

    public void setEligibleMessage(String eligibleMessage) {
        this.eligibleMessage = eligibleMessage;
    }
}
