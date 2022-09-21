package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Palak Mehta on 13-Jan-18.
 */

public class ProjectList implements Serializable{

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("ProjectThumbnail")
    @Expose
    private String projectThumbnail;
    @SerializedName("ImagesPath")
    @Expose
    private String imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
