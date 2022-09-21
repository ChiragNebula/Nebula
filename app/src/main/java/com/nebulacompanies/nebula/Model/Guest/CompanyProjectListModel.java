package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyProjectListModel {
    @SerializedName("Facility")
    @Expose
    private String facility;

    @SerializedName("Project")
    @Expose
    private String project;

    @SerializedName("ProjectFacilityDescription")
    @Expose
    private String projectFacilityDescription;

    @SerializedName("ImageProjectFacility")
    @Expose
    private String imageProjectFacility;

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectFacilityDescription() {
        return projectFacilityDescription;
    }

    public void setProjectFacilityDescription(String projectFacilityDescription) {
        this.projectFacilityDescription = projectFacilityDescription;
    }

    public String getImageProjectFacility() {
        return imageProjectFacility;
    }

    public void setImageProjectFacility(String imageProjectFacility) {
        this.imageProjectFacility = imageProjectFacility;
    }
}