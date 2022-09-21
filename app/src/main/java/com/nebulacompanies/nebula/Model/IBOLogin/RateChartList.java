package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 18-01-2018.
 */

public class RateChartList {

    @SerializedName("RateChartId")
    @Expose
    private String RateChartId;

    @SerializedName("Project")
    @Expose
    private String ProjectName;

    @SerializedName("ProjectIcon")
    @Expose
    private String ProjectThumbnail;


    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectThumbnail() {
        return ProjectThumbnail;
    }

    public void setProjectThumbnail(String projectThumbnail) {
        ProjectThumbnail = projectThumbnail;
    }

    public String getRateChartId() {
        return RateChartId;
    }

    public void setRateChartId(String rateChartId) {
        RateChartId = rateChartId;
    }
}
