package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewSiteProgress {

    @SerializedName("TotalRecord")
    @Expose
    private Integer totalRecord;


    @SerializedName("ProjectDescription")
    @Expose
    private String projectDescription;

    @SerializedName("result")
    private List<SiteProgressList> data = null;


    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<SiteProgressList> getData() {
        return data;
    }

    public void setData(List<SiteProgressList> data) {
        this.data = data;
    }
}
