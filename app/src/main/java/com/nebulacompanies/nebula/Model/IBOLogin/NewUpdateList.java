package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewUpdateList {

    @SerializedName("TotalRecords")
    @Expose
    private Integer TotalRecords;

    @SerializedName("displayList")
    @Expose
    private List<UpdateList> data = null;

    public Integer getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        TotalRecords = totalRecords;
    }

    public List<UpdateList> getData() {
        return data;
    }

    public void setData(List<UpdateList> data) {
        this.data = data;
    }
}
