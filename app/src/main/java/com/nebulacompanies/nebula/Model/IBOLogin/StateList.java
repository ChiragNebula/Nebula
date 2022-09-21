package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 01-03-2018.
 */

public class StateList {

    @SerializedName("StateId")
    @Expose
    private Integer StateId;

    @SerializedName("StateName")
    @Expose
    private String StateName;

    public Integer getStateId() {
        return StateId;
    }

    public void setStateId(Integer stateId) {
        StateId = stateId;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }
}
