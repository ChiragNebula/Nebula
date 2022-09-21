package com.nebulacompanies.nebula.Model.CustomerLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitList {
    @SerializedName("UnitId")
    @Expose
    private int unitid;
    @SerializedName("Unit")
    @Expose
    private String unit;

    @SerializedName("FloorName")
    @Expose
    private String floorname;

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloorname() {
        return floorname;
    }

    public void setFloorname(String floorname) {
        this.floorname = floorname;
    }
}
