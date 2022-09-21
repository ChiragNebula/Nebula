package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 17-Feb-18.
 */

public class RankHistoryList {

    @SerializedName("Rank")
    @Expose
    private String Rank;
    @SerializedName("CreateOn")
    @Expose
    private long CreateOn;

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public long getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(long createOn) {
        CreateOn = createOn;
    }
}
