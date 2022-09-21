package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sagar Virvani on 16-01-2018.
 */

public class VideoList implements Serializable {

    @SerializedName("VideoId")
    @Expose
    private Integer VideoId;

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("VideoType")
    @Expose
    private String Detail;

    @SerializedName("FileSize")
    @Expose
    private String Size;

    @SerializedName("Thumbnail")
    @Expose
    private String ThumbnailImages;

    @SerializedName("VideoFile")
    @Expose
    private String UploadPath;

    @SerializedName("ProjectLogo")
    @Expose
    private String ProjectLogo;


    public Integer getVideoId() {
        return VideoId;
    }

    public void setVideoId(Integer videoId) {
        VideoId = videoId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getThumbnailImages() {
        return ThumbnailImages;
    }

    public void setThumbnailImages(String thumbnailImages) {
        ThumbnailImages = thumbnailImages;
    }

    public String getUploadPath() {
        return UploadPath;
    }

    public void setUploadPath(String uploadPath) {
        UploadPath = uploadPath;
    }

    public String getProjectLogo() {
        return ProjectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        ProjectLogo = projectLogo;
    }
}
