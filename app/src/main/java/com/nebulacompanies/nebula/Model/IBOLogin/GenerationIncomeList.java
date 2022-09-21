package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 24-01-2018.
 */

public class GenerationIncomeList {

    @SerializedName("IncomeClosingDate")
    @Expose
    private long Date;

    @SerializedName("BVPercent")
    @Expose
    private double MyBvPercentage;


    @SerializedName("TotalBVCommission")
    @Expose
    private Integer TotalBVCommission;

    @SerializedName("SaleBV")
    @Expose
    private Integer BVFromSales;

    @SerializedName("FLBV")
    @Expose
    private Integer FLBV;

    @SerializedName("PBV")
    @Expose
    private Integer PBV;


    @SerializedName("PBVCommission")
    @Expose
    private Integer MyBVCommission;

    @SerializedName("DBV")
    @Expose
    private double DBV;

    @SerializedName("DBVCommission")
    @Expose
    private Integer DBVCommission;


    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public double getMyBvPercentage() {
        return MyBvPercentage;
    }

    public void setMyBvPercentage(double myBvPercentage) {
        MyBvPercentage = myBvPercentage;
    }

    public Integer getTotalBVCommission() {
        return TotalBVCommission;
    }

    public void setTotalBVCommission(Integer totalBVCommission) {
        TotalBVCommission = totalBVCommission;
    }

    public Integer getBVFromSales() {
        return BVFromSales;
    }

    public void setBVFromSales(Integer BVFromSales) {
        this.BVFromSales = BVFromSales;
    }

    public Integer getFLBV() {
        return FLBV;
    }

    public void setFLBV(Integer FLBV) {
        this.FLBV = FLBV;
    }

    public Integer getPBV() {
        return PBV;
    }

    public void setPBV(Integer PBV) {
        this.PBV = PBV;
    }

    public Integer getMyBVCommission() {
        return MyBVCommission;
    }

    public void setMyBVCommission(Integer myBVCommission) {
        MyBVCommission = myBVCommission;
    }

    public double getDBV() {
        return DBV;
    }

    public void setDBV(double DBV) {
        this.DBV = DBV;
    }

    public Integer getDBVCommission() {
        return DBVCommission;
    }

    public void setDBVCommission(Integer DBVCommission) {
        this.DBVCommission = DBVCommission;
    }
}
