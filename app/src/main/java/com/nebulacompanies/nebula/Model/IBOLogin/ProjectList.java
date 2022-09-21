package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 21-02-2018.
 */

public class ProjectList {

    @SerializedName("ProjectId")
    @Expose
    private Integer ProjectId;

    @SerializedName("ProjectName")
    @Expose
    private String ProjectName;

    @SerializedName("ProjectIcon")
    @Expose
    private String ProjectIcon;

    public Integer getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Integer projectId) {
        ProjectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectIcon() {
        return ProjectIcon;
    }

    public void setProjectIcon(String projectIcon) {
        ProjectIcon = projectIcon;
    }
}
