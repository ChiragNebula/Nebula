package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 08-Jan-18.
 */

public class Products {

    @SerializedName("ProjectId")
    @Expose
    private Integer projectId;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("ProjectIcon")
    @Expose
    private String projectIcon;
    @SerializedName("Description")
    @Expose
    private String description;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIcon() {
        return projectIcon;
    }

    public void setProjectIcon(String projectIcon) {
        this.projectIcon = projectIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
