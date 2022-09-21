package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Palak Mehta on 17-Jan-18.
 */

public class SiteProgressImageList implements Serializable{

    @SerializedName("ThumbnailIcon")
    @Expose
    private String thumbnail;
    @SerializedName("AlteredImage")
    @Expose
    private String subImages;
    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("CreatedOn")
    @Expose
    private long createdate;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(long createdate) {
        this.createdate = createdate;
    }
}
