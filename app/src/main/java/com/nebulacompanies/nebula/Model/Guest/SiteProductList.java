package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 16-Jan-18.
 */

public class SiteProductList {

    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("Image")
    @Expose
    private String projectThumbnail;
    @SerializedName("UnitType")
    @Expose
    private String detail;

    @SerializedName("ProjectId")
    @Expose
    private int ProjectId;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectThumbnail() {
        return projectThumbnail;
    }

    public void setProjectThumbnail(String projectThumbnail) {
        this.projectThumbnail = projectThumbnail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }
}
