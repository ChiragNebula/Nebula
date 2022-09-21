package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 23-01-2018.
 */

public class DreamVolumeList {

    @SerializedName("DisplayCreatedOn")
    @Expose
    private long Date;

    @SerializedName("DV")
    @Expose
    private Integer TotalDv;

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public Integer getTotalDv() {
        return TotalDv;
    }

    public void setTotalDv(Integer totalDv) {
        TotalDv = totalDv;
    }
}
