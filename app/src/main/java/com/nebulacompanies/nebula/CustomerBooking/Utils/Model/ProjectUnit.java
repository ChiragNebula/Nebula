package com.nebulacompanies.nebula.CustomerBooking.Utils.Model;

/**
 * Created by Nebula_18 on 11/17/2017.
 */

public class ProjectUnit {

    public Integer getUnitTypeId() {
        return UnitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        UnitTypeId = unitTypeId;
    }

    public Integer getFloorId() {
        return FloorId;
    }

    public void setFloorId(Integer floorId) {
        FloorId = floorId;
    }

    public Integer getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Integer projectId) {
        ProjectId = projectId;
    }

    private Integer UnitTypeId;
    private Integer FloorId;
    private Integer ProjectId;
}
