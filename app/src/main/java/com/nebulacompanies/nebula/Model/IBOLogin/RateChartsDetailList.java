package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 18-01-2018.
 */

public class RateChartsDetailList {

    @SerializedName("Thumbnail")
    @Expose
    private String RateChartThumbnail;

    @SerializedName("RateChartName")
    @Expose
    private String RateChartName;

    @SerializedName("RateChartFile")
    @Expose
    private String RateChartFilePath;

    @SerializedName("RateChartFileSize")
    @Expose
    private String RateChartFileSize;

    public String getRateChartThumbnail() {
        return RateChartThumbnail;
    }

    public void setRateChartThumbnail(String rateChartThumbnail) {
        RateChartThumbnail = rateChartThumbnail;
    }

    public String getRateChartName() {
        return RateChartName;
    }

    public void setRateChartName(String rateChartName) {
        RateChartName = rateChartName;
    }

    public String getRateChartFilePath() {
        return RateChartFilePath;
    }

    public void setRateChartFilePath(String rateChartFilePath) {
        RateChartFilePath = rateChartFilePath;
    }

    public String getRateChartFileSize() {
        return RateChartFileSize;
    }

    public void setRateChartFileSize(String rateChartFileSize) {
        RateChartFileSize = rateChartFileSize;
    }
}
