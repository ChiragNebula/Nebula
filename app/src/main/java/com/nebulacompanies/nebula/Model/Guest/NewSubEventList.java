package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewSubEventList  {

    @SerializedName("TotalRecord")
    @Expose
    private Integer TotalRecord;

    @SerializedName("Events")
    @Expose
    private List<SubEventList> data= null;

    public Integer getTotalRecord() {
        return TotalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        TotalRecord = totalRecord;
    }

    public List<SubEventList> getData() {
        return data;
    }

    public void setData(List<SubEventList> data) {
        this.data = data;
    }
}
