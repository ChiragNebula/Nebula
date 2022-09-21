package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 16-Apr-18.
 */

public class CRUpdatesList {

    @SerializedName("MessageStatus")
    @Expose
    private String MessageStatus;

    @SerializedName("Close_Message_Status")
    @Expose
    private String Close_Message_Status;

    public String getMessageStatus() {
        return MessageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        MessageStatus = messageStatus;
    }

    public String getClose_Message_Status() {
        return Close_Message_Status;
    }

    public void setClose_Message_Status(String close_Message_Status) {
        Close_Message_Status = close_Message_Status;
    }
}
