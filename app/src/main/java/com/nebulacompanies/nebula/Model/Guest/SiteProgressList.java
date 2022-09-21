package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 16-Jan-18.
 */

public class SiteProgressList {

    @SerializedName("MonthinText")
    @Expose
    private String monthinText;

    @SerializedName("Year")
    @Expose
    private String year;

    @SerializedName("ThumbnailIcon")
    @Expose
    private String thumbnailImage;


   /* @SerializedName("ThambleImage")
    @Expose
    private String thumbnailImage;*/


    @SerializedName("AlteredImage")
    @Expose
    private String rootImage;



    @SerializedName("Month")
    @Expose
    private Integer month;

    @SerializedName("CreatedOn")
    @Expose
    private long createdOn;

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public String getRootImage() {
        return rootImage;
    }

    public void setRootImage(String rootImage) {
        this.rootImage = rootImage;
    }

    public String getMonthinText() {
        return monthinText;
    }

    public void setMonthinText(String monthinText) {
        this.monthinText = monthinText;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

}
