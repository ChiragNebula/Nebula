package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 17-Jan-18.
 */

public class DownloadList {
    @SerializedName("ProjectName")
    @Expose
    private String ProjectName;

    @SerializedName("ProjectThumbnail")
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

}
