package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sagar Virvani on 13-04-2018.
 */

public class SubEventList implements Serializable {

    @SerializedName("EventId")
    @Expose
    private Integer eventID;

    @SerializedName("EventPic")
    @Expose
    private String EventPic;

    @SerializedName("CratedOn")
    @Expose
    private long CratedOn;

    @SerializedName("LikeCount")
    @Expose
    private Integer linkCount;

    @SerializedName("LikeByUser")
    @Expose
    private boolean likebyuser;

    @SerializedName("EventThumbnail")
    @Expose
    private String eventThumbnail;

    @SerializedName("EventDescription")
    private String  eventDescription;

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getEventPic() {
        return EventPic;
    }

    public void setEventPic(String eventPic) {
        EventPic = eventPic;
    }

    public long getCratedOn() {
        return CratedOn;
    }

    public void setCratedOn(long cratedOn) {
        CratedOn = cratedOn;
    }

    public Integer getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(Integer linkCount) {
        this.linkCount = linkCount;
    }

    public boolean isLikebyuser() {
        return likebyuser;
    }

    public void setLikebyuser(boolean likebyuser) {
        this.likebyuser = likebyuser;
    }

    public String getEventThumbnail() {
        return eventThumbnail;
    }

    public void setEventThumbnail(String eventThumbnail) {
        this.eventThumbnail = eventThumbnail;
    }
}