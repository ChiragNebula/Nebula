
package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrefrenceApp {

    @SerializedName("Floor")
    @Expose
    private String floor;
    @SerializedName("Project")
    @Expose
    private String project;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("FloorId")
    @Expose
    private Integer floorId;
    @SerializedName("ProjectId")
    @Expose
    private Integer projectId;
    @SerializedName("UnitTypeId")
    @Expose
    private Integer unitTypeId;
    @SerializedName("UnitID")
    @Expose
    private Integer unitID;
    @SerializedName("UnitType")
    @Expose
    private Object unitType;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public Object getUnitType() {
        return unitType;
    }

    public void setUnitType(Object unitType) {
        this.unitType = unitType;
    }

}
