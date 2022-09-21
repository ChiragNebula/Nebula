package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 14-Feb-18.
 */

public class RankAndVolumeList {

    @SerializedName("IBOID")
    @Expose
    private String IBOID;
    @SerializedName("CurrentRank")
    @Expose
    private String CurrentRank;
    @SerializedName("NextEligibleRank")
    @Expose
    private String NextEligibleRank;
    @SerializedName("TotalNV")
    @Expose
    private Integer TotalNV;
    @SerializedName("MinPNV")
    @Expose
    private Integer MinPNV;
    @SerializedName("LargestLeg")
    @Expose
    private String LargestLeg;
    @SerializedName("LargestLegNV")
    @Expose
    private Integer LargestLegNV;
    @SerializedName("Alpha")
    @Expose
    private Integer Alpha;
    @SerializedName("NextAlpha")
    @Expose
    private Integer NextAlpha;
    @SerializedName("AlphaPercent")
    @Expose
    private Integer AlphaPercent;
    @SerializedName("SecondLargestLeg")
    @Expose
    private String SecondLargestLeg;
    @SerializedName("SecondLargestLegNV")
    @Expose
    private Integer SecondLargestLegNV;
    @SerializedName("Beta")
    @Expose
    private Integer Beta;
    @SerializedName("NextBeta")
    @Expose
    private Integer NextBeta;
    @SerializedName("BetaPercent")
    @Expose
    private Integer BetaPercent;
   @SerializedName("ThirdPlusLeg")
    @Expose
    private List<String> ThirdPlusLeg;
    @SerializedName("ThirdPlusLegNV")
    @Expose
    private Integer ThirdPlusLegNV;
    @SerializedName("Gamma")
    @Expose
    private Integer Gamma;
    @SerializedName("NextGamma")
    @Expose
    private Integer NextGamma;
    @SerializedName("GammaPercent")
    @Expose
    private Integer GammaPercent;
    @SerializedName("MyBV")
    @Expose
    private Integer MyBV;
    @SerializedName("MonthlyBV")
    @Expose
    private double MonthlyBV;
    @SerializedName("BVPercent")
    @Expose
    private double BVPercent;
    @SerializedName("NextBVPercent")
    @Expose
    private double NextBVPercent;
    @SerializedName("TotalBV")
    @Expose
    private Integer TotalBV;
    @SerializedName("NextBV")
    @Expose
    private Integer NextBV;
    @SerializedName("BVInfo")
    @Expose
    private List<BVInfoList> BVInfo;
    @SerializedName("TotalDV")
    @Expose
    private Integer TotalDV;

    @SerializedName("CurrentRankIcon")
    @Expose
    private String CurrentRankIcon;

    @SerializedName("NextEligibleRankIcon")
    @Expose
    private String NextEligibleRankIcon;


    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getCurrentRank() {
        return CurrentRank;
    }

    public void setCurrentRank(String currentRank) {
        CurrentRank = currentRank;
    }

    public String getNextEligibleRank() {
        return NextEligibleRank;
    }

    public void setNextEligibleRank(String nextEligibleRank) {
        NextEligibleRank = nextEligibleRank;
    }

    public Integer getTotalNV() {
        return TotalNV;
    }

    public void setTotalNV(Integer totalNV) {
        TotalNV = totalNV;
    }

    public Integer getMinPNV() {
        return MinPNV;
    }

    public void setMinPNV(Integer minPNV) {
        MinPNV = minPNV;
    }

    public String getLargestLeg() {
        return LargestLeg;
    }

    public void setLargestLeg(String largestLeg) {
        LargestLeg = largestLeg;
    }

    public Integer getLargestLegNV() {
        return LargestLegNV;
    }

    public void setLargestLegNV(Integer largestLegNV) {
        LargestLegNV = largestLegNV;
    }

    public Integer getAlpha() {
        return Alpha;
    }

    public void setAlpha(Integer alpha) {
        Alpha = alpha;
    }

    public Integer getNextAlpha() {
        return NextAlpha;
    }

    public void setNextAlpha(Integer nextAlpha) {
        NextAlpha = nextAlpha;
    }

    public Integer getAlphaPercent() {
        return AlphaPercent;
    }

    public void setAlphaPercent(Integer alphaPercent) {
        AlphaPercent = alphaPercent;
    }

    public String getSecondLargestLeg() {
        return SecondLargestLeg;
    }

    public void setSecondLargestLeg(String secondLargestLeg) {
        SecondLargestLeg = secondLargestLeg;
    }

    public Integer getSecondLargestLegNV() {
        return SecondLargestLegNV;
    }

    public void setSecondLargestLegNV(Integer secondLargestLegNV) {
        SecondLargestLegNV = secondLargestLegNV;
    }

    public Integer getBeta() {
        return Beta;
    }

    public void setBeta(Integer beta) {
        Beta = beta;
    }

    public Integer getNextBeta() {
        return NextBeta;
    }

    public void setNextBeta(Integer nextBeta) {
        NextBeta = nextBeta;
    }

    public Integer getBetaPercent() {
        return BetaPercent;
    }

    public void setBetaPercent(Integer betaPercent) {
        BetaPercent = betaPercent;
    }

    public List<String> getThirdPlusLeg() {
        return ThirdPlusLeg;
    }

    public void setThirdPlusLeg(List<String> thirdPlusLeg) {
        ThirdPlusLeg = thirdPlusLeg;
    }

    public Integer getThirdPlusLegNV() {
        return ThirdPlusLegNV;
    }

    public void setThirdPlusLegNV(Integer thirdPlusLegNV) {
        ThirdPlusLegNV = thirdPlusLegNV;
    }

    public Integer getGamma() {
        return Gamma;
    }

    public void setGamma(Integer gamma) {
        Gamma = gamma;
    }

    public Integer getNextGamma() {
        return NextGamma;
    }

    public void setNextGamma(Integer nextGamma) {
        NextGamma = nextGamma;
    }

    public Integer getGammaPercent() {
        return GammaPercent;
    }

    public void setGammaPercent(Integer gammaPercent) {
        GammaPercent = gammaPercent;
    }

    public Integer getMyBV() {
        return MyBV;
    }

    public void setMyBV(Integer myBV) {
        MyBV = myBV;
    }

    public double getMonthlyBV() {
        return MonthlyBV;
    }

    public void setMonthlyBV(double monthlyBV) {
        MonthlyBV = monthlyBV;
    }

    public double getBVPercent() {
        return BVPercent;
    }

    public void setBVPercent(double BVPercent) {
        this.BVPercent = BVPercent;
    }

    public double getNextBVPercent() {
        return NextBVPercent;
    }

    public void setNextBVPercent(double nextBVPercent) {
        NextBVPercent = nextBVPercent;
    }

    public Integer getTotalBV() {
        return TotalBV;
    }

    public void setTotalBV(Integer totalBV) {
        TotalBV = totalBV;
    }

    public Integer getNextBV() {
        return NextBV;
    }

    public void setNextBV(Integer nextBV) {
        NextBV = nextBV;
    }

    public List<BVInfoList> getBVInfo() {
        return BVInfo;
    }

    public void setBVInfo(List<BVInfoList> BVInfo) {
        this.BVInfo = BVInfo;
    }

    public Integer getTotalDV() {
        return TotalDV;
    }

    public void setTotalDV(Integer totalDV) {
        TotalDV = totalDV;
    }

    public String getCurrentRankIcon() {
        return CurrentRankIcon;
    }

    public void setCurrentRankIcon(String currentRankIcon) {
        CurrentRankIcon = currentRankIcon;
    }

    public String getNextEligibleRankIcon() {
        return NextEligibleRankIcon;
    }

    public void setNextEligibleRankIcon(String nextEligibleRankIcon) {
        NextEligibleRankIcon = nextEligibleRankIcon;
    }
}
