package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 17-01-2018.
 */

public class NotificationList {

    @SerializedName("Icon")
    @Expose
    private String Icon;

    @SerializedName("Content")
    @Expose
    private String Message;

    @SerializedName("CratedOn")
    @Expose
    private long CreatedDate;

    @SerializedName("Category")
    @Expose
    private String Category;

    @SerializedName("ProjectId")
    @Expose
    private int ProjectId;

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        CreatedDate = createdDate;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }
}
