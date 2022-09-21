package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 14-Feb-18.
 */

public class GetIBORankList {

    @SerializedName("RanKID")
    @Expose
    private String RanKID;
    @SerializedName("Rank")
    @Expose
    private String Rank;

    public String getRanKID() {
        return RanKID;
    }

    public void setRanKID(String ranKID) {
        RanKID = ranKID;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }
}
