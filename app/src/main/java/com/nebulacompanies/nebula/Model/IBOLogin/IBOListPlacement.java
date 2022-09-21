package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 26-Feb-18.
 */

public class IBOListPlacement {

    @SerializedName("StatusCode")
    @Expose
    private Integer StatusCode;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private IBOList data = null;

    public Integer getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Integer StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public IBOList getData() {
        return data;
    }

    public void setData(IBOList data) {
        this.data = data;
    }

}
