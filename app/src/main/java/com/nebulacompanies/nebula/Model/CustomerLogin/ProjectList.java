package com.nebulacompanies.nebula.Model.CustomerLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectList {
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("ProjectName")
    @Expose
    private String ProjectName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }
}
