package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 20-Feb-18.
 */

public class UpdateList {

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Icon")
    @Expose
    private String Icon;
    @SerializedName("Information")
    @Expose
    private String Information;
    @SerializedName("CreatedOnInfo")
    @Expose
    private Long CreatedOnInfo;
    @SerializedName("NotificationType")
    @Expose
    private String NotificationType;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public Long getCreatedOnInfo() {
        return CreatedOnInfo;
    }

    public void setCreatedOnInfo(Long createdOnInfo) {
        CreatedOnInfo = createdOnInfo;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String notificationType) {
        NotificationType = notificationType;
    }

}
