package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 13-Jan-18.
 */

public class EventList {

    @SerializedName("EventId")
    @Expose
    private Integer eventID;
    @SerializedName("Category")
    @Expose
    private String eventName;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Icon")
    @Expose
    private String eventlogo;
    @SerializedName("CratedOn")
    @Expose
    private long eventDate;


    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventlogo() {
        return eventlogo;
    }

    public void setEventlogo(String eventlogo) {
        this.eventlogo = eventlogo;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }
}
