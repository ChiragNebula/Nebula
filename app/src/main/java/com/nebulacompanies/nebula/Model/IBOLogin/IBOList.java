package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Palak Mehta on 20-Apr-18.
 */

public class IBOList {

    @SerializedName("TotalRecord")
    @Expose
    private Integer TotalRecord;

    @SerializedName("MyDownline")
    @Expose
    private List<IBOListDetails> MyDownline;

    public Integer getTotalRecord() {
        return TotalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        TotalRecord = totalRecord;
    }

    public List<IBOListDetails> getMyDownline() {
        return MyDownline;
    }

    public void setMyDownline(List<IBOListDetails> myDownline) {
        MyDownline = myDownline;
    }
}
