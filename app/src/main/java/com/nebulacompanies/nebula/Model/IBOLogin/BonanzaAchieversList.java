package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 11-Apr-18.
 */

public class BonanzaAchieversList {

    @SerializedName("MiniBonanzaCount")
    @Expose
    private Integer MiniBonanzaCount;

    @SerializedName("Leg")
    @Expose
    private Integer Leg;

    public Integer getMiniBonanzaCount() {
        return MiniBonanzaCount;
    }

    public void setMiniBonanzaCount(Integer miniBonanzaCount) {
        MiniBonanzaCount = miniBonanzaCount;
    }

    public Integer getLeg() {
        return Leg;
    }

    public void setLeg(Integer leg) {
        Leg = leg;
    }
}
